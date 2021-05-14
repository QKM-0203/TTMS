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
     * @param cinemaId
     * @return
     */
    @GetMapping("/getHalls/{cinemaId}")
    public List<MovieHall> getHalls(@PathVariable("cinemaId") Long cinemaId){
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
    public int delHall(@RequestParam("id") String id){
        return  hallService.delHall(Long.parseLong(id));
    }


    /**
     * 编辑演出厅
     * @param movieHall
     * @return
     */
    @PutMapping("/editHall")
    public int updateHall(@RequestBody MovieHall movieHall){
        return  hallService.updateHall(movieHall);
    }

}
