package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.HallService;
import com.qkm.TTMS.service.UserOrderService;
import com.qkm.TTMS.service.impl.AreaCinemaServiceImpl;
import com.qkm.TTMS.service.impl.CinemaMoviesServiceImpl;
import com.qkm.TTMS.service.impl.HallServiceImpl;
import com.qkm.TTMS.service.impl.UserOrderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CinemaController {


    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserMapper movieUserMapper;
    private final AreaCinemasMapper areaCinemasMapper;

    private final AreaCinemaService areaCinemaSer;

    public CinemaController(AreaCinemaServiceImpl areaCinemaSer, AreaCinemasMapper areaCinemasMapper, MovieUserMapper movieUserMapper, MovieUserRolesMapper movieUserRolesMapper) {
        this.areaCinemaSer = areaCinemaSer;
        this.areaCinemasMapper = areaCinemasMapper;
        this.movieUserMapper = movieUserMapper;
        this.movieUserRolesMapper = movieUserRolesMapper;
    }

    /**
     * 获取某个区的含有某部电影的所有电影院信息
     * @param areaName
     * @param movieId
     * @return
     */
    @GetMapping("/getAreaCinemas")
    public List<AreaCinemas> getAreaCinemas(@RequestParam("areaName")String areaName, @RequestParam("movieId")Long movieId){
        return areaCinemaSer.getCinemaMoviesByCinemaId(areaName, movieId);
    }


    /**
     * 获取所有电影院的信息
     * @return
     */
    @GetMapping("/getCinemas")
    public List<AreaCinemas> getCinemas(){
        return areaCinemaSer.getAll();
    }

    /**
     * 删除电影院
     * @param cinemaId
     * @return
     */
    @DeleteMapping("/delCinemas")
    public int delCinemas(@RequestParam("cinemaId") Long cinemaId){
        return areaCinemaSer.deleteById(cinemaId);
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
