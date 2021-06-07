package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MoviePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviePlanMapper extends BaseMapper<MoviePlan> {
    List<MoviePlan> getMoviePlan(@Param("movieId")int movieId, @Param("cinemaId")int cinemaId);
    List<MoviePlan> getMoviePlanFilterDate(@Param("movieId")int movieId, @Param("cinemaId")int cinemaId);
    int deleteByCinemaMovieId(@Param("cinemaMovieId") int cinemaMovieId);


}




