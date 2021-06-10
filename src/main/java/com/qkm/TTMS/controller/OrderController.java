package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.mapper.MovieSellMapper;
import com.qkm.TTMS.mapper.UserOrderMapper;
import com.qkm.TTMS.service.*;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {


    private final UserOrderMapper userOrderMapper;
    private final MovieSellMapper mapper;
    private final CommonService commonService;
    private final CinemaMoviesService cinemaMoviesService;
    private final HallSeatMapper hallSeatMapper;
    private final RedisTemplate<String,Object> redisTemplate;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;
    private final SeatService seatSer;
    private final UserOrderService userOrderService;


    public OrderController(CinemaMoviesService cinemaMoviesService, SeatService seatSer, AreaCinemaService areaCinemaSer, MovieService movieSer, RedisTemplate<String, Object> redisTemplate, HallSeatMapper hallSeatMapper, UserOrderService userOrderService, CommonService commonService, MovieSellMapper mapper, UserOrderMapper userOrderMapper) {
        this.seatSer = seatSer;
        this.areaCinemaSer = areaCinemaSer;
        this.movieSer = movieSer;
        this.redisTemplate = redisTemplate;
        this.hallSeatMapper = hallSeatMapper;
        this.userOrderService = userOrderService;
        this.cinemaMoviesService =  cinemaMoviesService;
        this.commonService = commonService;
        this.mapper = mapper;
        this.userOrderMapper = userOrderMapper;
    }


    /**
     * 前端将选的座位也要发过来,锁住座位,未支付状态
     * @param userOrder   订单信息
     * @param moviePlanId   电影的演出计划Id
     * @return  是否选座成功
     */
    @PostMapping("/saveOrder/{moviePlanId}")
    public int saveOrder(@RequestBody UserOrder userOrder,@PathVariable("moviePlanId")int moviePlanId){
        Map<String, String> seatByRedis = seatSer.getSeatByRedis(moviePlanId);
        List<HallSeat> hallSeatList  = userOrder.getHallSeatList();
        userOrder.setPayTime(new Date());
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.watch(String.valueOf(moviePlanId));
                redisTemplate.multi();
                //存座位
                for (HallSeat seat : hallSeatList) {
                    if ("0".equals(seatByRedis.get(seat.getSeatLine() + "," + String.valueOf(seat.getSeatColumn())))) {
                    seatByRedis.put((seat.getSeatLine()) + "," + String.valueOf(seat.getSeatColumn()),"1" );
                     }else {
                        return new ArrayList<>();
                    }
                }
                redisTemplate.opsForValue().set(String.valueOf(moviePlanId), seatByRedis);
                return operations.exec();
            }
        });

        Map<String, String> seatByRedis1 = seatSer.getSeatByRedis(moviePlanId);
        System.out.println(seatByRedis1);

        if(!execute.isEmpty()){
            //存订单
            userOrderService.saveOrder(userOrder);
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


    @GetMapping("/sellAddLock/{moviePlanId}")
    public int sellAddLock(@RequestBody UserOrder userOrder,@PathVariable("moviePlanId")int moviePlanId){
       return saveOrder(userOrder,moviePlanId);
    }

    /**
     * 获取订单剩余时间
     */
    @GetMapping("/getOrderTime/{orderId}")
    public Map<String,Object> getOrderTime(@PathVariable("orderId")int orderId){
        Long expire = redisTemplate.getExpire("order" + orderId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("time", expire);
        return  map;
    }

    /**
     * 确认付钱后执行
     * @param userOrder   订单信息
     * @return  是否支付成功
     */
    @PostMapping("/saveMoney")
    public int saveMoney(@RequestBody UserOrder userOrder){
        redisTemplate.delete("order"+userOrder.getId());
        commonService.addAllMoney(userOrder);
        userOrderService.updateOrderStatusById("已支付",userOrder.getId());
        return 1;
    }

    /**
     * 售票员确认付钱后执行
     * @return  是否支付成功
     */
    @PostMapping("/sellSaveMoney/{sellId}/{sellMoney}")
    public int sellSaveMoney(@RequestBody UserOrder userOrder ,@PathVariable("sellId")int sellId,@PathVariable("sellMoney")float sellMoney){
        userOrderMapper.insert(userOrder);
        commonService.addAllMoney(userOrder);
        return mapper.updateBySellId(sellId,sellMoney);
    }


    /**
     * 退订单
     */
    @DeleteMapping("/delOrder")
    public int backOrder(@RequestBody UserOrder userOrder){
//        Date movieStartTime = userOrder.getMovieStartTime();
//        if(movieStartTime.getTime() - new Date().getTime() < 1800000L ){
//           //电影开始半小时前不能退订单
//            return 0;
//        }
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
