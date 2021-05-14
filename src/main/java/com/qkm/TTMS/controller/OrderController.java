package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {


    private CinemaMoviesService cinemaMoviesService;
    private final HallSeatMapper hallSeatMapper;
    private final RedisTemplate<String,Object> redisTemplate;
    private final AreaCinemaService areaCinemaSer;
    private final MovieService movieSer;
    private final SeatService seatSer;
    private final UserOrderService userOrderService;
    public OrderController(SeatService seatSer, AreaCinemaService areaCinemaSer, MovieService movieSer, RedisTemplate<String, Object> redisTemplate, HallSeatMapper hallSeatMapper, UserOrderService userOrderService) {
        this.seatSer = seatSer;
        this.areaCinemaSer = areaCinemaSer;
        this.movieSer = movieSer;
        this.redisTemplate = redisTemplate;
        this.hallSeatMapper = hallSeatMapper;
        this.userOrderService = userOrderService;
    }

     /**
     * 前端将选的座位也要发过来,锁住座位,未支付状态
     * @param userOrder
     * @param moviePlanId
     * @return
     */
    @PostMapping("/saveOrder")
    public Long saveOrder(@RequestBody UserOrder userOrder,@RequestParam("moviePlanId")Long moviePlanId){
        Map<String, String> seatByRedis = seatSer.getSeatByRedis(moviePlanId);
        redisTemplate.watch(String.valueOf(moviePlanId));
        //存座位
        List<HallSeat> hallSeatList = userOrder.getHallSeatList();
        redisTemplate.multi();
        for(int  j = 0; j < hallSeatList.size(); j++){
            if(seatByRedis.containsKey((String.valueOf(hallSeatList.get(j).getSeatLine()))+String.valueOf(hallSeatList.get(j).getSeatColumn()))){
                seatByRedis.put((String.valueOf(hallSeatList.get(j).getSeatLine())) + String.valueOf(hallSeatList.get(j).getSeatColumn()),"1");
            }
        }
        redisTemplate.opsForHash().putAll(String.valueOf(moviePlanId),seatByRedis);
        List<Object> exec = redisTemplate.exec();
        if(!exec.isEmpty()){
            for (HallSeat hallSeat : hallSeatList) {
                hallSeat.setOrderId(userOrder.getId());
                seatSer.saveSeat(hallSeat);
            }
            //存订单
            userOrderService.saveOrder(userOrder);

            redisTemplate.opsForValue().set("order"+userOrder.getId(), userOrder,  10, TimeUnit.SECONDS);
            return userOrder.getId();
        }else{
            return 0L;
        }

    }

    /**
     * 获取订单剩余时间
     */
    @GetMapping("/getOrederTime")
    public Map<String,Object> getOrderTime(@RequestParam("orderId")Long orderId){

        Long expire = redisTemplate.getExpire("order" + orderId);
        UserOrder userOrder = (UserOrder)redisTemplate.opsForValue().get("order" + orderId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("time",expire);
        map.put("order",userOrder);
        return  map;
    }

    /**
     * 确认付钱后执行
     * @param userOrder
     * @return
     */
    @PostMapping("/saveMoney")
    public int saveMoney(@RequestBody UserOrder userOrder){
        //存票房
        int i1 = movieSer.addMoney(userOrder.getOrderMoney(), userOrder.getMovieId());
        //存电影院赚的钱
        int i2 = areaCinemaSer.addMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());
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
        movieSer.downMoney(userOrder.getOrderMoney(), userOrder.getMovieId());
        areaCinemaSer.downMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());

        long idByCinemaIdAndMovieId = cinemaMoviesService.getIdByCinemaIdAndMovieId(userOrder.getCinemaId(), userOrder.getMovieId());
        //获取缓存中的座位进行修改
        Map<String, String> seatByRedis = seatSer.getSeatByRedis(idByCinemaIdAndMovieId);
        List<HallSeat> hallSeatList = userOrder.getHallSeatList();
        for (HallSeat hallSeat : hallSeatList) {
            seatByRedis.put(String.valueOf(hallSeat.getSeatLine())+String.valueOf(hallSeat.getSeatColumn()),"0");
        }
        hallSeatMapper.delByOrderId(userOrder.getId());
        return  userOrderService.updateOrderStatusById("退款成功",userOrder.getId());
    }


    /**
     * 查询整个电影院的订单
     * @param cinemaId
     * @return
     */
    @GetMapping("/getOrders")
    public List<UserOrder> getOrders(@RequestParam("cinemaId") Long cinemaId){
        return userOrderService.getAllByCinemaId(cinemaId);
    }

    /**
     * 查询自己的所有订单
     */
    @GetMapping("/getOrdersBySelf")
    public List<UserOrder> getOrdersBySelf(@RequestParam("userId") Long userId){
        return userOrderService.getAllByUserId(userId);
    }


    /**
     * 删除自己的订单
     */
    @DeleteMapping("/delOrdersBySelf")
    public int delOrdersBySelf(@RequestParam("orderId")Long orderId){
        return userOrderService.delById(orderId);
    }


}
