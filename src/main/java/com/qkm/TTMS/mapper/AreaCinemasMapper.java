package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.AreaCinemas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AreaCinemasMapper extends BaseMapper<AreaCinemas> {
    List<AreaCinemas> getCinemaMoviesByCinemaId(@Param("areaName") String areaName, @Param("movieId") int movieId);
    AreaCinemas getAllById(@Param("id") int id);
    List<Integer> getListID();
    List<AreaCinemas> getAll();
    int deleteById(@Param("cinemaId") int cinemaId);
    int addMoney(@Param("money")Double money,@Param("cinemaId") int cinemaId);
    int downMoney(@Param("money")Double money,@Param("cinemaId") int cinemaId);
    List<AreaCinemas> getAllByAreaName(@Param("cinemaName") String cinemaName);
}




