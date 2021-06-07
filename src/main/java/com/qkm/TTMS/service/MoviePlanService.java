package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MoviePlan;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface MoviePlanService {
    List<MoviePlan> getMoviePlan(int movieId,int cinemaId);
    List<MoviePlan> getMoviePlanByDate(int movieId, int cinemaId);
    HashMap<Date, List<MoviePlan>> ClassficationDate(List<MoviePlan> moviePlanList);

}
