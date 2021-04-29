package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.mapper.MovieHallMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HallCon {


//    @PostMapping("/addHall")
//    public String addHall(@RequestBody JSONObject json){
//        System.out.println(json);
//        System.out.println(JSON.toJSONString(json));
//        return JSON.toJSONString(json);
//    }

    private final MovieHallMapper movieHallMapper;

    public HallCon(MovieHallMapper movieHallMapper) {
        this.movieHallMapper = movieHallMapper;
    }


    @GetMapping("/getHalls")
    public String getHalls(){
        List<MovieHall> movieHalls = movieHallMapper.selectList(null);
        System.out.println(movieHalls);
        return JSON.toJSONString(movieHalls);
    }


    @PostMapping("/addHall")
    public String addHall(@RequestBody MovieHall movieHall){
        System.out.println(movieHall);
        return JSON.toJSONString(movieHall);
    }

}
