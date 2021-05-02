package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.mapper.AreaCinemasMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CinemaCon {

    private final RedisTemplate<String,Object> redisTemplate;

    private final AreaCinemasMapper areaCinemasMapper;

    public CinemaCon(AreaCinemasMapper areaCinemasMapper, RedisTemplate<String, Object> redisTemplate) {
        this.areaCinemasMapper = areaCinemasMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取某个区的所有电影院信息
     * @param areaName
     * @param movieId
     * @return
     */
    @GetMapping("/getCinemasByArea")
    public List<AreaCinemas> getCinemas(@Param("areaName")String areaName, @Param("movieId")Long movieId){
        List<AreaCinemas> cinemaMoviesByCinemaId = areaCinemasMapper.getCinemaMoviesByCinemaId(areaName, movieId);
        redisTemplate.opsForValue().set(areaName,cinemaMoviesByCinemaId);
        return cinemaMoviesByCinemaId;
    }


    /**
     * 获取某个电影院信息
     * @param areaName
     * @param movieId
     * @return
     */
    @GetMapping("/getPlanBymovieIdAndAreaName")
    public void getCinema(@Param("areaName")String areaName, @Param("movieId")Long movieId){
        List<AreaCinemas> cinemasList = ( List<AreaCinemas>)redisTemplate.opsForValue().get(areaName);

    }

}
