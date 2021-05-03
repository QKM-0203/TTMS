package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MoviePlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoviePlanSer {
    List<MoviePlan> getMoviePlan(Long movieId,Long cinemaId);
    int deleteByCinemaMovieId( Long cinemaMovieId);
}
