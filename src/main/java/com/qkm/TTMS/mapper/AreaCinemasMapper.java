package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.AreaCinemas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkm.TTMS.entity.CinemaMovies;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.AreaCinemas
 */
@Repository
public interface AreaCinemasMapper extends BaseMapper<AreaCinemas> {
    List<AreaCinemas> getCinemaMoviesByCinemaId(@Param("areaName") String areaName, @Param("movieId") Long movieId);
}




