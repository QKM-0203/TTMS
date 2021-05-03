package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.entity.MovieUser;
import com.qkm.TTMS.entity.MovieUserRoles;
import com.qkm.TTMS.mapper.AreaCinemasMapper;
import com.qkm.TTMS.mapper.MovieUserMapper;
import com.qkm.TTMS.mapper.MovieUserRolesMapper;
import com.qkm.TTMS.service.AreaCinemaSer;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CinemaCon {

    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserMapper movieUserMapper;
    private final AreaCinemasMapper areaCinemasMapper;

    private final AreaCinemaSerImpl areaCinemaSerImpl;

    public CinemaCon(AreaCinemaSerImpl areaCinemaSerImpl, AreaCinemasMapper areaCinemasMapper, MovieUserMapper movieUserMapper, MovieUserRolesMapper movieUserRolesMapper) {
        this.areaCinemaSerImpl = areaCinemaSerImpl;

        this.areaCinemasMapper = areaCinemasMapper;
        this.movieUserMapper = movieUserMapper;
        this.movieUserRolesMapper = movieUserRolesMapper;
    }

    /**
     * 获取某个区的所有电影院信息
     * @param areaName
     * @param movieId
     * @return
     */
    @GetMapping("/getAreaCinemas")
    public List<AreaCinemas> getAreaCinemas(@Param("areaName")String areaName, @Param("movieId")Long movieId){
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
    @GetMapping("/delCinemas")
    public int delCinemas(@RequestParam("cinemaId") Long cinemaId){
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

    @PostMapping("/editCinema")
    public int updateCinema(AreaCinemas areaCinemas){
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
