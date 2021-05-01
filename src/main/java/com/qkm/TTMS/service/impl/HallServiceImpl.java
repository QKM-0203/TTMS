package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.mapper.MovieHallMapper;
import com.qkm.TTMS.service.HallService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HallServiceImpl implements HallService {

    private final MovieHallMapper movieHallMapper;

    public HallServiceImpl(MovieHallMapper movieHallMapper) {
        this.movieHallMapper = movieHallMapper;
    }

    /**
     * 删除演出厅
     * @param id
     */
    public int delHall(Long id){
        try{
            movieHallMapper.deleteById(id);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }


    /**
     * 查询所有的演出厅
     * @param cinemaId
     * @return
     */
    @Override
    public List<MovieHall> getHalls(Long cinemaId) {
        QueryWrapper<MovieHall> movieHallQueryWrapper = new QueryWrapper<>();
        movieHallQueryWrapper.ge("cinema_id",cinemaId);
        List<MovieHall> movieHalls = movieHallMapper.selectList(movieHallQueryWrapper);
        return  movieHalls;
    }


    /**
     * 更新演出厅
     */ @Override
    public int updateHall(MovieHall movieHall) {
        try{
            movieHallMapper.updateById(movieHall);
            return 1;
        }catch(Exception e){
            return 0;
        }

    }

    /**
     * 增加演出厅
     * @param movieHall
     */
    @Override
    public Long addHall( MovieHall movieHall) {
        try{
            movieHallMapper.insert(movieHall);
            return movieHall.getId();
        }catch(Exception e){
            return 0L;
        }

    }
}
