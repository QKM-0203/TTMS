package com.qkm.TTMS.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {


    private final CommonService commonService;
    private final CinemaMoviesService cinemaMoviesService;
    private final HallSeatMapper hallSeatMapper;
    private final RedisTemplate<String,Object> redisTemplate;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;
    private final SeatService seatSer;
    private final UserOrderService userOrderService;
    public OrderController(CinemaMoviesService cinemaMoviesService, SeatService seatSer, AreaCinemaService areaCinemaSer, MovieService movieSer, RedisTemplate<String, Object> redisTemplate, HallSeatMapper hallSeatMapper, UserOrderService userOrderService, CommonService commonService) {
        this.seatSer = seatSer;
        this.areaCinemaSer = areaCinemaSer;
        this.movieSer = movieSer;
        this.redisTemplate = redisTemplate;
        this.hallSeatMapper = hallSeatMapper;
        this.userOrderService = userOrderService;
        this.cinemaMoviesService =  cinemaMoviesService;
        this.commonService = commonService;
    }

     /**
     * 前端将选的座位也要发过来,锁住座位,未支付状态
     * @param userOrder   订单信息
     * @param moviePlanId   电影的演出计划Id
     * @return  是否选座成功
     */
    @PostMapping("/saveOrder/{moviePlanId}")
    public int saveOrder(@RequestBody UserOrder userOrder,@PathVariable("moviePlanId")int moviePlanId){
        //存订单
        userOrderService.saveOrder(userOrder);

        Map<String, String> seatByRedis = seatSer.getSeatByRedis(moviePlanId);
        redisTemplate.watch(String.valueOf(moviePlanId));
        //存座位
        List<HallSeat> hallSeatList = userOrder.getHallSeatList();
        redisTemplate.multi();
        for (HallSeat seat : hallSeatList) {
            if (seatByRedis.containsKey((seat.getSeatLine()) + ","+String.valueOf(seat.getSeatColumn()))) {
                seatByRedis.put((seat.getSeatLine()) +"," +String.valueOf(seat.getSeatColumn()), "1");
            }
        }
        redisTemplate.opsForHash().putAll(String.valueOf(moviePlanId),seatByRedis);
        List<Object> exec = redisTemplate.exec();
        if(!exec.isEmpty()){
            for (HallSeat hallSeat : hallSeatList) {
                hallSeat.setOrderId(userOrder.getId());
                seatSer.saveSeat(hallSeat);
            }
            redisTemplate.opsForValue().set("order"+userOrder.getId(), userOrder,  60*5, TimeUnit.SECONDS);
            return userOrder.getId();
        }else{
            return 0;
        }

    }

    /**
     * 获取订单剩余时间
     */
    @GetMapping("/getOrderTime/{orderId}")
    public Map<String,Object> getOrderTime(@PathVariable("orderId")int orderId){

        Long expire = redisTemplate.getExpire("order" + orderId);
        UserOrder userOrder = (UserOrder)redisTemplate.opsForValue().get("order" + orderId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("time", expire);
        map.put("order",userOrder);
        return  map;
    }

    /**
     * 确认付钱后执行
     * @param userOrder   订单信息
     * @return  是否支付成功
     */
    @PostMapping("/saveMoney")
    public int saveMoney(@RequestBody UserOrder userOrder){
        //存票房
        int i1 = movieSer.addMoney(userOrder.getOrderMoney(), userOrder.getMovieId());
        //存电影院赚的钱
         areaCinemaSer.addMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());
         //存储电影院的某部电影赚得钱
        int idByCinemaIdAndMovieId = cinemaMoviesService.getIdByCinemaIdAndMovieId(userOrder.getCinemaId(), userOrder.getMovieId());
        cinemaMoviesService.addMoney(userOrder.getOrderMoney(),idByCinemaIdAndMovieId);
        userOrderService.updateOrderStatusById("已支付",userOrder.getId());
        return i1;

    }

    /**
     * 退订单
     */
    @DeleteMapping("/delOrder")
    public int backOrder(@RequestBody UserOrder userOrder){
        Date movieStartTime = userOrder.getMovieStartTime();
        if(movieStartTime.getTime() - new Date().getTime() < 1800000L ){
           //电影开始半小时前不能退订单
            return 0;
        }
        int idByCinemaIdAndMovieId = cinemaMoviesService.getIdByCinemaIdAndMovieId(userOrder.getCinemaId(), userOrder.getMovieId());
        movieSer.downMoney(userOrder.getOrderMoney(), userOrder.getMovieId());
        areaCinemaSer.downMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());
        cinemaMoviesService.downMoney(userOrder.getOrderMoney(),idByCinemaIdAndMovieId);
       //获取缓存中的座位进行修改
        Map<String, String> seatByRedis = seatSer.getSeatByRedis(userOrder.getPlanId());
        List<HallSeat> hallSeatList = userOrder.getHallSeatList();
        for (HallSeat hallSeat : hallSeatList) {
            seatByRedis.put(hallSeat.getSeatLine() +","+String.valueOf(hallSeat.getSeatColumn()),"0");
        }
        redisTemplate.opsForHash().putAll(String.valueOf(userOrder.getPlanId()),seatByRedis);
        hallSeatMapper.delByOrderId(userOrder.getId());
        return  userOrderService.updateOrderStatusById("退款成功",userOrder.getId());
    }

    /**
     *根据订单时间查信息
     */
    @GetMapping("/getOrdersByTime/{cinemaId}/{start}/{end}/{page}")
    public List<UserOrder> getOrders(@PathVariable("cinemaId")int cinemaId,@PathVariable("page")int page,
                                     @PathVariable("start")String start,@PathVariable("end")String end) throws ParseException {
        List<UserOrder> allByCinemaId = userOrderService.getOrdersByCinemaId(cinemaId,-1);
        ArrayList<UserOrder> userOrders = new ArrayList<>();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < allByCinemaId.size(); i++) {
            String format = sdf.format(allByCinemaId.get(i).getMovieStartTime());
            System.out.println(format);
            if((format.compareTo(end) < 0 && format.compareTo(start) > 0 )|| format.compareTo(start) == 0 || format.compareTo(end) == 0){
                userOrders.add(allByCinemaId.get(i));
            }
        }
        List<UserOrder> userOrdersList = (List<UserOrder>) commonService.getPage(userOrders,page,5);
        if(userOrdersList == null){
            return null;
        }
        userOrdersList.add(new UserOrder(String.valueOf(commonService.justPage(userOrders,5))));
        return userOrdersList;
    }


    /**
     * 查询整个电影院的订单
     * @param cinemaId  影院Id
     * @return   所有的订单
     */
    @GetMapping("/getOrders/{cinemaId}/{page}")
    public List<UserOrder> getOrders(@PathVariable("cinemaId") int cinemaId,@PathVariable("page")int page){
        return userOrderService.getOrdersByCinemaId(cinemaId,page);
    }

    /**
     * 查询自己的所有订单
     */
    @GetMapping("/getOrdersBySelf/{userId}/{page}")
    public List<UserOrder> getOrdersBySelf(@PathVariable("userId") int userId,@PathVariable("page")int page){
        return userOrderService.getOrdersByUserId(userId,page);
    }


    /**
     * 删除自己的订单
     */
    @DeleteMapping("/delOrdersBySelf/{orderId}")
    public int delOrdersBySelf(@PathVariable("orderId")int orderId){
        return userOrderService.delById(orderId);
    }


}
