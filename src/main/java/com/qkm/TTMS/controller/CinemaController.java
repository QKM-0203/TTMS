package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.impl.AreaCinemaServiceImpl;
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
     * @param areaName   地区的名字
     * @param movieId  电影的Id
     * @return  所有的电影院信息
     */
    @GetMapping("/getAreaCinemas/{areaName}/{movieId}")
    public List<AreaCinemas> getAreaCinemas(@PathVariable("areaName")String areaName, @PathVariable("movieId")int movieId){
        return areaCinemaSer.getCinemaMoviesByCinemaId(areaName, movieId);
    }


    /**
     * 获取所有电影院的信息
     * @return  所有的电影院的信息
     */
    @GetMapping("/getCinemas")
    public List<AreaCinemas> getCinemas(){
        return areaCinemaSer.getAll();
    }

    /**
     * 删除电影院
     * @param cinemaId  电影院的Id
     * @return  是否删除成功
     */
    @DeleteMapping("/delCinemas/{cinemaId}")
    public int delCinemas(@PathVariable("cinemaId") int cinemaId){
        return areaCinemaSer.deleteById(cinemaId);
    }

    /**
     * 增加电影院
     * @param areaCinemas  电影院的信息
     * @return  是否增加成功
     */
    @PostMapping("/addCinemas")
    public int addCinemas(@RequestBody AreaCinemas areaCinemas){
       return  areaCinemasMapper.insert(areaCinemas);
    }

    /**
     * 编辑电影院
     * @param areaCinemas  电影院的信息
     * @return  是否编辑成功
     */

    @PutMapping("/editCinema")
    public int updateCinema(@RequestBody AreaCinemas areaCinemas){
        return areaCinemasMapper.updateById(areaCinemas);
    }

    /**
     * 为影院添加管理人员
     * @param movieUser  人员信息
     * @return   是否增加成功
     */
    @PostMapping("/addAdmin")
    public int addAdmin(@RequestBody MovieUser movieUser){
        movieUserMapper.insert(movieUser);
        MovieUserRoles movieUserRoles = new MovieUserRoles();
        movieUserRoles.setUserId(movieUser.getId());
        movieUserRoles.setRoleId(1);
        movieUserRolesMapper.insert(movieUserRoles);
        return  movieUser.getId();
    }


}
