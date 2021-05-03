package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MoviePlan;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.MoviePlanSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviePlanSerImpl implements MoviePlanSer {
    private final MoviePlanMapper moviePlanMapper;

    public MoviePlanSerImpl(MoviePlanMapper moviePlanMapper) {
        this.moviePlanMapper = moviePlanMapper;
    }

    @Override
    public List<MoviePlan> getMoviePlan(Long movieId, Long cinemaId) {
        return moviePlanMapper.getMoviePlan(movieId,cinemaId);
    }

    @Override
    public int deleteByCinemaMovieId(Long cinemaMovieId) {
        return moviePlanMapper.deleteByCinemaMovieId(cinemaMovieId);
    }
}
