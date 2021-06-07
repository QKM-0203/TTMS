package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.service.HallService;
import com.qkm.TTMS.service.impl.HallServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HallController {



    private final HallService hallService;

    public HallController(HallServiceImpl hallService) {
        this.hallService = hallService;
    }


    /**
     * 获取某个影院的所有演出厅
     * @param cinemaId  电影院Id
     * @return   获取的电影的信息
     */
    @GetMapping("/getHalls/{cinemaId}/{page}")
    public List<MovieHall> getHalls(@PathVariable("cinemaId") int cinemaId,@PathVariable("page")int page){
        return  hallService.getHalls(cinemaId,page);
    }

    /**
     * 获取某个影院的所有演出厅不分页
     * @param cinemaId  电影院Id
     * @return   获取的电影的信息
     */
    @GetMapping("/getHalls/{cinemaId}")
    public List<MovieHall> getHallsNotPage(@PathVariable("cinemaId") int cinemaId){
        return  hallService.getHallsNotPage(cinemaId);
    }


    /**
     * 增加演出厅
     * @param movieHall 演出厅的信息
     * @return  是否成功
     */
    @PostMapping("/addHall")
    public int addHall(@RequestBody MovieHall movieHall){
        hallService.addHall(movieHall);
        return movieHall.getId();
    }

    /**
     * 删除演出厅
     * @param id   演出厅的Id
     * @return  是否成功
     */
    @DeleteMapping("/delHall/{id}/{cinemaId}/{page}")
    public List<MovieHall> delHall(@PathVariable("id") String id, @PathVariable("page")int page, @PathVariable("cinemaId")int cinemaId){
        hallService.delHall(Integer.parseInt(id));
        return getHalls(cinemaId,page);
    }


    /**
     * 编辑演出厅
     * @param movieHall  演出厅的信息
     * @return  是否更新成功
     */
    @PutMapping("/editHall")
    public int updateHall(@RequestBody MovieHall movieHall){
        return  hallService.updateHall(movieHall);
    }

}
