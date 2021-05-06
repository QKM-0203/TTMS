package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaSer;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.CinemaMoviesSerImpl;
import com.qkm.TTMS.service.impl.HallServiceImpl;
import com.qkm.TTMS.service.impl.UserOrderImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CinemaCon {

    private final HallServiceImpl hallService;
    private final HallSeatMapper hallSeatMapper;
    private final MoviePlanMapper moviePlanMapper;
    private final UserOrderImpl userOrder;

    private final CinemaMoviesSerImpl cinemaMoviesSer;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserMapper movieUserMapper;
    private final AreaCinemasMapper areaCinemasMapper;

    private final AreaCinemaSerImpl areaCinemaSerImpl;

    public CinemaCon(AreaCinemaSerImpl areaCinemaSerImpl, AreaCinemasMapper areaCinemasMapper, MovieUserMapper movieUserMapper, MovieUserRolesMapper movieUserRolesMapper, CinemaMoviesSerImpl cinemaMoviesSer, UserOrderImpl userOrder, MoviePlanMapper moviePlanMapper, HallSeatMapper hallSeatMapper, HallServiceImpl hallService) {
        this.areaCinemaSerImpl = areaCinemaSerImpl;

        this.areaCinemasMapper = areaCinemasMapper;
        this.movieUserMapper = movieUserMapper;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.userOrder = userOrder;
        this.moviePlanMapper = moviePlanMapper;
        this.hallSeatMapper = hallSeatMapper;
        this.hallService = hallService;
    }

    /**
     * 获取某个区的含有某部电影的所有电影院信息
     * @param areaName
     * @param movieId
     * @return
     */
    @GetMapping("/getAreaCinemas")
    public List<AreaCinemas> getAreaCinemas(@RequestParam("areaName")String areaName, @RequestParam("movieId")Long movieId){
        return areaCinemaSerImpl.getCinemaMoviesByCinemaId(areaName, movieId);
    }


    /**
     * 获取所有电影院的信息
     * @return
     */
    @GetMapping("/getCinemas")
    public List<AreaCinemas> getCinemas(){
        return areaCinemaSerImpl.getAll();
    }

    /**
     * 删除电影院
     * @param cinemaId
     * @return
     */
    @DeleteMapping("/delCinemas")
    public int delCinemas(@RequestParam("cinemaId") Long cinemaId){
        List<Long> listIdByCinemaId = cinemaMoviesSer.getListIdByCinemaId(cinemaId);
        List<Long> moviePlans = moviePlanMapper.selectListCMId(listIdByCinemaId);
        hallSeatMapper.deleteByMoviePlanIds(moviePlans);
        moviePlanMapper.deleteByCinemaMovieIds(listIdByCinemaId);
        cinemaMoviesSer.deleteByCinemaId(cinemaId);
        userOrder.deleteByCinemaId(cinemaId);
        hallService.deleteByCinemaId(cinemaId);
        return areaCinemaSerImpl.deleteById(cinemaId);
    }

    /**
     * 增加电影院
     * @param areaCinemas
     * @return
     */
    @PostMapping("/addCinemas")
    public int addCinemas(@RequestBody AreaCinemas areaCinemas){
       return  areaCinemasMapper.insert(areaCinemas);
    }

    /**
     * 编辑电影院
     * @param areaCinemas
     * @return
     */

    @PutMapping("/editCinema")
    public int updateCinema(@RequestBody AreaCinemas areaCinemas){
        return areaCinemasMapper.updateById(areaCinemas);
    }

    /**
     * 为影院添加管理人员
     * @param movieUser
     * @return
     */
    @PostMapping("/addAdmin")
    public Long addAdmin(@RequestBody MovieUser movieUser){
        int insert = movieUserMapper.insert(movieUser);
        MovieUserRoles movieUserRoles = new MovieUserRoles();
        movieUserRoles.setUserId(movieUser.getId());
        movieUserRoles.setRoleId(1);
        movieUserRolesMapper.insert(movieUserRoles);
        return  movieUser.getId();
    }


}
