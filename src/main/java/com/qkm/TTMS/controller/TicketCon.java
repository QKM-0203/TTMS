package com.qkm.TTMS.controller;

import com.qkm.TTMS.config.RedisConfig;
import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import com.qkm.TTMS.service.impl.SeatSerImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TicketCon {

    private final MovieSerImpl movieSer;

    public TicketCon(AreaCinemaSerImpl areaCinemaSerImpl, SeatSerImpl seatSerImpl, MovieSerImpl movieSer) {
        this.areaCinemaSerImpl = areaCinemaSerImpl;
        this.seatSerImpl = seatSerImpl;
        this.movieSer = movieSer;
    }
    private final AreaCinemaSerImpl areaCinemaSerImpl;

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


    @GetMapping("/getSeatAndOrder")
    public Map<String,Object> getOrder(@RequestBody UserOrder userOrder, @Param("planId") Long planId){
        List<HallSeat> seatByPlanId = seatSerImpl.getSeatByPlanId(planId);
        HashMap<String, Object> Map = new HashMap<String, Object>();
        Map.put("seat",seatByPlanId);
        Map.put("order",userOrder);
        return Map;
    }



}
