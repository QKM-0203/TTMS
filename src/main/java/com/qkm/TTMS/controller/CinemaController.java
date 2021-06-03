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
     * 获取含有某部电影的所有电影院信息
     * @param  page   分页
     * @param movieId  电影的Id
     * @return  所有的电影院信息
     */
    @GetMapping("/getCinemas/{movieId}/{page}")
    public List<AreaCinemas> getCinemasByMovieId(@PathVariable("page")int page, @PathVariable("movieId")int movieId){
        return areaCinemaSer.getCinemaByCinemaId(page, movieId);
    }


    /**
     * 省市区的三级联动
     * @param cityId
     * @param movieId
     * @param provinceId
     * @param areaId
     * @param page
     * @return
     */
    @GetMapping("/getCinemaByArea/{provinceId}/{cityId}/{areaId}/{movieId}/{page}")
    public Map<String,Object> getCinemaByArea(@PathVariable("cityId")int cityId,@PathVariable("movieId")int movieId,
       @PathVariable("provinceId")int provinceId,@PathVariable("areaId")int areaId,@PathVariable("page")int page){
       if(provinceId != 0 && cityId != 0 && areaId != 0){
           return areaCinemaSer.getCinemasByProvinceAndCityAndArea(areaId,movieId,page);
       } else if(provinceId != 0 && cityId == 0 && areaId == 0 ){
           return areaCinemaSer.getCinemasByProvince(provinceId,movieId,page);
       } else if(provinceId != 0 && cityId != 0){
           return areaCinemaSer.getCinemasByProvinceAndCity(cityId,movieId,page);
       }
       return null;
    }


    /**
     * 获取所有电影院的信息
     * @return  所有的电影院的信息
     */
    @GetMapping("/getCinemas/{page}")
    public List<AreaCinemas> getCinemas(@PathVariable("page")int page){
        return areaCinemaSer.getAll(page);
    }

    /**
     * 删除电影院
     * @param cinemaId  电影院的Id
     * @return  是否删除成功
     */
    @DeleteMapping("/delCinemas/{cinemaId}/{page}")
    public List<AreaCinemas> delCinemas(@PathVariable("cinemaId") int cinemaId,@PathVariable("page")int page){
        areaCinemaSer.deleteById(cinemaId);
        return getCinemas(page);
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
