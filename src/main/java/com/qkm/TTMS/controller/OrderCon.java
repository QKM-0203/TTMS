package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.mapper.UserOrderMapper;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import com.qkm.TTMS.service.impl.SeatSerImpl;
import com.qkm.TTMS.service.impl.UserOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCon {


    private final HallSeatMapper hallSeatMapper;
    private final RedisTemplate<String,Object> redisTemplate;
    private final AreaCinemaSerImpl areaCinemaSer;
    private final MovieSerImpl movieSer;
    private final SeatSerImpl seatSerImpl;
    private final UserOrderImpl userOrderImpl;

    public OrderCon(UserOrderImpl userOrderImpl, SeatSerImpl seatSerImpl, AreaCinemaSerImpl areaCinemaSer, MovieSerImpl movieSer, RedisTemplate<String, Object> redisTemplate, HallSeatMapper hallSeatMapper) {
        this.userOrderImpl = userOrderImpl;
        this.seatSerImpl = seatSerImpl;
        this.areaCinemaSer = areaCinemaSer;
        this.movieSer = movieSer;
        this.redisTemplate = redisTemplate;
        this.hallSeatMapper = hallSeatMapper;
    }

    /**
     * 前端将选的座位也要发过来
     * @param
     * @retur
     */
    @GetMapping("/saveOrder")
    public Long saveOrder(@RequestBody UserOrder userOrder){
        //存订单
        userOrderImpl.saveOrder(userOrder);
        redisTemplate.opsForValue().set("order"+userOrder.getId(), userOrder,  10, TimeUnit.SECONDS);
        //存座位
        List<HallSeat> hallSeatList = userOrder.getHallSeatList();
        for (HallSeat hallSeat : hallSeatList) {
            hallSeat.setOrderId(userOrder.getId());
            seatSerImpl.saveSeat(hallSeat);
        }
        return userOrder.getId();
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
    @GetMapping("/saveMoney")
    public int saveMoney(@RequestBody UserOrder userOrder){
        //存票房
        int i1 = movieSer.updateMoney(userOrder.getOrderMoney(), userOrder.getMovieName());
        //存电影院赚的钱
        int i2 = areaCinemaSer.updateMoney(userOrder.getOrderMoney(), userOrder.getCinemaId());
        userOrderImpl.updateOrderStatusById("已支付",userOrder.getId());
        return i1;

    }


    /**
     * 查询整个电影院的订单
     * @param cinemaId
     * @return
     */
    @GetMapping("/getOrders")
    public List<UserOrder> getOrders(@RequestParam("cinema_id") Long cinemaId){
        return userOrderImpl.getAllByCinemaId(cinemaId);
    }

    /**
     * 查询自己的所有订单
     */
    @GetMapping("/getOrdersBySelf")
    public List<UserOrder> getOrdersBySelf(@RequestParam("userId") Long userId){
        return userOrderImpl.getAllByUserId(userId);
    }


    /**
     * 删除自己的订单
     */
    @GetMapping("/delOrdersBySelf")
    public int delOrdersBySelf(@RequestParam("orderId")Long orderId){
        int i = hallSeatMapper.delByOrderId(orderId);
        int i1 = userOrderImpl.delById(orderId);
        return i1;
    }


}
