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


    /**
     * 获取某个影院的所有演出厅
     * @param cinemaId
     * @return
     */
    @GetMapping("/getHalls")
    public List<MovieHall> getHalls(Long cinemaId){
        return  hallService.getHalls(cinemaId);
    }


    /**
     * 增加演出厅
     * @param movieHall
     * @return
     */
    @PostMapping("/addHall")
    public long addHall(@RequestBody MovieHall movieHall){
        Long i = hallService.addHall(movieHall);
        return movieHall.getId();
    }

    /**
     * 删除演出厅
     * @param id
     * @return
     */
    @DeleteMapping("/delHall")
    public int delHall( @RequestParam("id") String id){
        return  hallService.delHall(Long.parseLong(id));

    }


    /**
     * 编辑演出厅
     * @param movieHall
     * @return
     */
    @PutMapping("/editHall")
    public int updateHall(MovieHall movieHall){
        return  hallService.updateHall(movieHall);

    }

}
