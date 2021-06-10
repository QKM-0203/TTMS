package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TicketController {


    private final CommonService commonService;
    private final MovieService movieService;
    private final MovieUserService movieUserService;
    private final CinemaMoviesService cinemaMoviesService;
    private final SeatService seatService;
    private final HallSeatMapper hallSeatMapper;

    public TicketController(HallSeatMapper hallSeatMapper, SeatService seatService, CinemaMoviesService cinemaMoviesService, MovieUserService movieUserService, MovieService movieService, CommonService commonService) {
        this.hallSeatMapper = hallSeatMapper;
        this.seatService = seatService;
        this.cinemaMoviesService = cinemaMoviesService;
        this.movieUserService = movieUserService;
        this.movieService = movieService;
        this.commonService = commonService;
    }


    /**
     * 获取座位
     *
     * @param planId 计划Id
     * @return 选座页面所有的信息
     */
    @PostMapping("/getSeatAndOrder/{planId}")
    public Map<String, Object> getOrder(@PathVariable("planId") int planId) {
        Map<String, String> execute = seatService.getSeatByRedis(planId);
        HashMap<String, Object> Map = new HashMap<>();
        Map.put("seat", execute);
        return Map;
    }


    /**
     * 退某几张票
     *
     * @param hallSeats 座位
     * @return 是否退票成功
     */
    @DeleteMapping("/delTicket")
    public int backTicket(@RequestBody List<HallSeat> hallSeats) {
        int i = 1;
        for (HallSeat hallSeat : hallSeats) {
            i = hallSeatMapper.delByOrderIdAndSeatColumnAndSeatLine(hallSeat.getOrderId(), hallSeat.getSeatColumn(), hallSeat.getSeatLine());
        }
        return i;
    }

    /**
     * 管理员查看售票员的票房和当前电影的在当前电影院售卖的票房
     */
    @GetMapping("/getMoney/{cinemaId}/{page}")
    public Map<String, Object> getMoney(@PathVariable("page") int page, @PathVariable("cinemaId") int cinemaId) {
        List<Movie> movieAndMoney = cinemaMoviesService.getMovieAndMoney(cinemaId, page);
        List<MovieUser> moneyBySell = movieUserService.getMoneyBySell(cinemaId, page);
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("cinema", movieAndMoney);
        stringObjectHashMap.put("sell", moneyBySell);
        return stringObjectHashMap;
    }



    /**
     * 经理看所有的电影的票房
     */
    @GetMapping("/getAllMoney/{page}")
    public List<Movie> getAllMoney(@PathVariable("page") int page) {
        List<Movie> movies = movieService.getMovies();
        Collections.sort(movies ,new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.getMovieMoney() > o2.getMovieMoney()){
                    return -1;
                }else if(o1.getMovieMoney() < o2.getMovieMoney()){
                    return 1;
                }else{
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        List<Movie> movieList = (List<Movie>) commonService.getPage(movies,page,10);
        movieList.add(new Movie(commonService.justPage(movies,10)));
        return movieList;
    }


}

