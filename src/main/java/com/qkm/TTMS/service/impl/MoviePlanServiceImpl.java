package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MoviePlan;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.MoviePlanService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoviePlanServiceImpl implements MoviePlanService {
    private final MoviePlanMapper moviePlanMapper;

    public MoviePlanServiceImpl(MoviePlanMapper moviePlanMapper) {
        this.moviePlanMapper = moviePlanMapper;
    }

    @Override
    public List<MoviePlan> getMoviePlan(Long movieId, Long cinemaId) {
        return moviePlanMapper.getMoviePlan(movieId,cinemaId);
    }



  }
