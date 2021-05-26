package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TicketController {



    private final SeatService seatService;
    private final HallSeatMapper hallSeatMapper;

    public TicketController(HallSeatMapper hallSeatMapper, SeatService seatService) {
        this.hallSeatMapper = hallSeatMapper;
        this.seatService = seatService;
    }


    /**
     * 获取座位
     * @param userOrder
     * @param planId
     * @return
     */
    @PostMapping("/getSeatAndOrder/{planId}")
    public Map<String,Object> getOrder(@RequestBody UserOrder userOrder, @PathVariable("planId") Long planId){
        Map<String, String> execute = seatService.getSeatByRedis(planId);
        HashMap<String, Object> Map = new HashMap<String, Object>();
        Map.put("seat",execute);
        Map.put("order",userOrder);
        if(userOrder.getMovieStartTime().getTime() - new Date().getTime() < 300000L){
            Map.put("sign","电影开场5分钟前不再售票");
        }else{
            Map.put("sign",1);
        }
        return Map;
    }



    /**
     * 退某几张票
     * @param hallSeats
     * @return
     */
    @DeleteMapping("/delTicket")
    public int backTicket(@RequestBody List<HallSeat> hallSeats){
        int i = 1;
        for (HallSeat hallSeat : hallSeats) {
            i = hallSeatMapper.delByOrderIdAndSeatColumnAndSeatLine(hallSeat.getOrderId(),hallSeat.getSeatColumn(),hallSeat.getSeatLine());
        }
        return i;
    }





}
