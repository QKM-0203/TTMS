package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MoviePlan;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.MoviePlanService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class MoviePlanServiceImpl implements MoviePlanService {
    private final MoviePlanMapper moviePlanMapper;

    public MoviePlanServiceImpl(MoviePlanMapper moviePlanMapper) {
        this.moviePlanMapper = moviePlanMapper;
    }

    @Override
    public List<MoviePlan> getMoviePlan(int movieId, int cinemaId) {
        return moviePlanMapper.getMoviePlan(movieId,cinemaId);
    }
    @Override
    public List<MoviePlan> getMoviePlanByDate(int movieId, int cinemaId) {
        return moviePlanMapper.getMoviePlanFilterDate(movieId,cinemaId);
    }

    @Override
    public HashMap<Date, List<MoviePlan>> ClassficationDate(List<MoviePlan> moviePlanList) {
        HashMap<Date, List<MoviePlan>> dataMap = new HashMap<>();
        for (MoviePlan plan : moviePlanList) {
            if(dataMap.containsKey(plan.getPlanDate())){
                dataMap.get(plan.getPlanDate()).add(plan);
            }else{
                ArrayList<MoviePlan> moviePlans = new ArrayList<>();
                moviePlans.add(plan);
                dataMap.put(plan.getPlanDate(),moviePlans);
            }
        }
        return  dataMap;
    }




}
