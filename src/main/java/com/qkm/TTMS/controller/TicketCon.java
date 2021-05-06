package com.qkm.TTMS.controller;

import com.qkm.TTMS.config.RedisConfig;
import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import com.qkm.TTMS.service.impl.SeatSerImpl;
import com.qkm.TTMS.service.impl.UserOrderImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TicketCon {


    private final UserOrderImpl userOrderImpl;
    private final HallSeatMapper hallSeatMapper;
    public TicketCon(SeatSerImpl seatSerImpl, HallSeatMapper hallSeatMapper, UserOrderImpl userOrderImpl) {
        this.seatSerImpl = seatSerImpl;
        this.hallSeatMapper = hallSeatMapper;
        this.userOrderImpl = userOrderImpl;
    }


    private final SeatSerImpl seatSerImpl;

//    @GetMapping("/getSeatAndOrder")
//    public Map<String,Object> getOrder(@Param("status") int status, @Param("movieId") long movieId, @Param("cinemaId") Long cinemaId
//            , @Param("hallName")String hallName, @Param("startTime") Date startTime, @Param("ticketMoney") Double ticketMoney
//            ,@Param("userId") Long userId,@Param("planId") Long planId){
//        //电影的信息
//        Movie movieByStatus = movieSer.getMovieByStatus(status, movieId);
//        //电影院的信息
//        AreaCinemas allById = areaCinemaSerImpl.getAllById(cinemaId);
//        //订单信息
//        UserOrder userOrder = new UserOrder();
//        userOrder.setCinemaId(cinemaId);
//        userOrder.setCinemaName(allById.getCinemaName());
//        userOrder.setMovieStartTime(startTime);
//        userOrder.setTicketMoney(ticketMoney);
//        userOrder.setHallName(hallName);
//        userOrder.setUserId(userId);
//        userOrder.setMovieName(movieByStatus.getMovieName());
//        List<HallSeat> seatByPlanId = seatSerImpl.getSeatByPlanId(planId);
//        HashMap<String, Object> Map = new HashMap<String, Object>();
//        Map.put("seat",seatByPlanId);
//        Map.put("order",userOrder);
//        Map.put("movie",movieByStatus);
//        return Map;
//    }


    @PostMapping("/getSeatAndOrder")
    public Map<String,Object> getOrder(@RequestBody UserOrder userOrder, @RequestParam("planId") Long planId){
        List<HallSeat> seatByPlanId = seatSerImpl.getSeatByPlanId(planId);
        HashMap<String, Object> Map = new HashMap<String, Object>();
        Map.put("seat",seatByPlanId);
        Map.put("order",userOrder);
        return Map;
    }


    /**
     * 退票
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
