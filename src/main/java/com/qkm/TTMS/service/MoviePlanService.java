package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MoviePlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoviePlanService {
    List<MoviePlan> getMoviePlan(Long movieId,Long cinemaId);
}
