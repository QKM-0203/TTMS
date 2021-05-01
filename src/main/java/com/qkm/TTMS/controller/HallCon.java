package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.mapper.MovieHallMapper;
import com.qkm.TTMS.service.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class HallCon {


//    @PostMapping("/addHall")
//    public String addHall(@RequestBody JSONObject json){
//        System.out.println(json);
//        System.out.println(JSON.toJSONString(json));
//        return JSON.toJSONString(json);
//    }

    private final HallServiceImpl hallService;

    public HallCon(HallServiceImpl hallService) {
        this.hallService = hallService;
    }


    @GetMapping("/getHalls")
    public String getHalls(Long cinemaId){
        List<MovieHall> halls = hallService.getHalls(cinemaId);
        return JSON.toJSONString(halls);
    }


    @PostMapping("/addHall")
    public String addHall(@RequestBody MovieHall movieHall){
        Long i = hallService.addHall(movieHall);
        return JSON.toJSONString(i);
    }

    @GetMapping("/delHall")
    public String delHall( @RequestParam("id") String id){
        int i = hallService.delHall(Long.parseLong(id));
        return JSON.toJSONString(i);
    }


    @PostMapping("/editHall")
    public String updateHall(MovieHall movieHall){
        int i = hallService.updateHall(movieHall);
        return  JSON.toJSONString(i);
    }

}
