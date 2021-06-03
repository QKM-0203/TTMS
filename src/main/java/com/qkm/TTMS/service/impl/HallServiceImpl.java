package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.entity.MovieUser;
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
    public int delHall(int id){
            try {
                movieHallMapper.deleteById(id);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }

    }


    /**
     * 查询所有的演出厅分页
     * @param cinemaId
     * @return
     */
    @Override
    public List<MovieHall> getHalls(int cinemaId,int page) {
        Page<MovieHall> movieHallPage = new Page<>(page,5,true);
        IPage<MovieHall> movieHallIPage = movieHallMapper.selectHallPage(movieHallPage, cinemaId);
        List<MovieHall> movieHallIPageList = movieHallIPage.getRecords();
        movieHallIPageList.add(new MovieHall(String.valueOf(movieHallIPage.getPages())));
        return movieHallIPageList;
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
    public int addHall( MovieHall movieHall) {
        try{
            movieHallMapper.insert(movieHall);
            return movieHall.getId();
        }catch(Exception e){
            return 0;
        }

    }

    @Override
    public int deleteByCinemaId(int cinemaId) {
        return movieHallMapper.deleteByCinemaId(cinemaId);
    }
}
