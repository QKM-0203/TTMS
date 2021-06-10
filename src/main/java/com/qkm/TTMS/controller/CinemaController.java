package com.qkm.TTMS.controller;

import com.qkm.TTMS.config.SecurityConfig;
import com.qkm.TTMS.entity.*;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.CommonService;
import com.qkm.TTMS.service.impl.AreaCinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
public class CinemaController {


    @Autowired
    private  SecurityConfig securityConfig;
    private final CommonService commonService;
    private final CinemaMoviesService cinemaMoviesService;
    private final MovieUserRolesMapper movieUserRolesMapper;
    private final MovieUserMapper movieUserMapper;
    private final AreaCinemasMapper areaCinemasMapper;

    private final AreaCinemaService areaCinemaSer;

    public CinemaController(AreaCinemaServiceImpl areaCinemaSer, AreaCinemasMapper areaCinemasMapper, MovieUserMapper movieUserMapper, MovieUserRolesMapper movieUserRolesMapper, CinemaMoviesService cinemaMoviesService, CommonService commonService) {
        this.areaCinemaSer = areaCinemaSer;
        this.areaCinemasMapper = areaCinemasMapper;
        this.movieUserMapper = movieUserMapper;
        this.movieUserRolesMapper = movieUserRolesMapper;
        this.cinemaMoviesService = cinemaMoviesService;
        this.commonService = commonService;
    }

    /**
     * 获取含有某部电影的所有电影院信息
     * @param  page   分页
     * @param movieId  电影的Id
     * @return  所有的电影院信息
     */
    @GetMapping("/getCinemas/{movieId}/{page}")
    public List<AreaCinemas> getCinemasByMovieId(@PathVariable("page")int page, @PathVariable("movieId")int movieId){
        return areaCinemaSer.getCinemaByMovieId(page, movieId);
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
       if(cityId == 0 && provinceId == 0 && areaId == 0 ){
           Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("data",getCinemasByMovieId(page, movieId));
            stringObjectMap.put("local",null);
            return stringObjectMap;
       }
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
     * 省市区的三级联动,没有发送电影id
     * @param cityId
     * @param provinceId
     * @param areaId
     * @param page
     * @return
     */
    @GetMapping("/getCinemaByArea/{provinceId}/{cityId}/{areaId}/{page}")
    public Map<String,Object> getCinemaByArea(@PathVariable("cityId")int cityId, @PathVariable("provinceId")int provinceId,@PathVariable("areaId")int areaId,@PathVariable("page")int page){
        if(cityId == 0 && provinceId == 0 && areaId == 0 ){
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("data",getCinemas(page));
            stringObjectMap.put("local",null);
            return stringObjectMap;
        }
        if(provinceId != 0 && cityId != 0 && areaId != 0){
            return areaCinemaSer.getAllCinemasByProvinceAndCityAndArea(areaId,page);
        } else if(provinceId != 0 && cityId == 0 && areaId == 0 ){
            return areaCinemaSer.getAllCinemasByProvince(provinceId,page);
        } else if(provinceId != 0 && cityId != 0){
            return areaCinemaSer.getAllCinemasByProvinceAndCity(cityId,page);
        }
        return null;
    }


    /**
     * 获取所有电影院的信息
     * @return  所有的电影院的信息
     */
    @GetMapping("/getCinemas/{page}")
    public List<AreaCinemas> getCinemas(@PathVariable("page")int page){
        return areaCinemaSer.getAllCinemas(page);
    }


    /**
     * 删除电影院
     * @param cinemaId  电影院的Id
     * @return  是否删除成功
     */
    @DeleteMapping("/delCinemas/{cinemaId}/{page}")
    public List<AreaCinemas> delCinemas(@PathVariable("cinemaId") int cinemaId,@PathVariable("page")int page){
        areaCinemaSer.deleteById(cinemaId);
        cinemaMoviesService.deleteByCinemaId(cinemaId);
        return getCinemas(page);
    }


    /**
     * 增加电影院
     * @param areaCinemas  电影院的信息
     * @return  是否增加成功
     */
    @PostMapping("/addCinemas")
    public int addCinemas(@RequestBody AreaCinemas areaCinemas){
        //插入电影院
        areaCinemasMapper.insert(areaCinemas);
        //生成默认账号
        MovieUser movieUser = new MovieUser();
        movieUser.setAccounts(areaCinemas.getCinemaEmail());
        movieUser.setPassword(securityConfig.encode().encode("123456"));
        //插入默认的账号
        movieUserMapper.insert(movieUser);
        //为该人员设置权限
        MovieUserRoles movieUserRoles = new MovieUserRoles();
        movieUserRoles.setUserId(movieUser.getId());
        movieUserRoles.setRoleId(3);
        movieUserRolesMapper.insert(movieUserRoles);
        return  areaCinemas.getId();
    }

    /**
     * 为影院修改图片
     * @param request
     * @return
     */
    @PostMapping("/setPictureForCinema/{id}")
    public String setPictureForCinema( MultipartHttpServletRequest request,@PathVariable("id")int id){
        MultipartFile icon = request.getFile("icon");
        List<String> strings = commonService.uploadPictures(icon);
        if("上传成功".equals(strings.get(strings.size()-1))){
            areaCinemasMapper.updateCinemaPictureById(strings.get(0),id);
            return "上传成功";
        }else if("上传失败因为存在文件为空".equals(strings.get(strings.size()-1))){
            return "上传失败因为存在文件为空";
        }else{
            return "文件上传失败";
        }
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



}
