package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.MoviePlan;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.impl.AreaCinemaSerImpl;
import com.qkm.TTMS.service.impl.CinemaMoviesSerImpl;
import com.qkm.TTMS.service.impl.MoviePlanSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PlanCon {

    private final MoviePlanMapper moviePlanMapper;
    private final CinemaMoviesSerImpl cinemaMoviesSer;
    private final AreaCinemaSerImpl areaCinemaSerImpl;
    private  final MovieSerImpl movieSerImpl;
    private  final MoviePlanSerImpl moviePlanSerImpl;


    public PlanCon(MovieSerImpl movieSerImpl, MoviePlanSerImpl moviePlanSerImpl, AreaCinemaSerImpl areaCinemaSerImpl, CinemaMoviesSerImpl cinemaMoviesSer, MoviePlanMapper moviePlanMapper) {
        this.movieSerImpl = movieSerImpl;
        this.moviePlanSerImpl = moviePlanSerImpl;
        this.areaCinemaSerImpl = areaCinemaSerImpl;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.moviePlanMapper = moviePlanMapper;
    }


    /**
     * 增加演出计划
     * @param moviePlans
     * @return
     */
    @PostMapping("/addPlan")
    public Long addPlan(@RequestBody MoviePlan moviePlans, @RequestParam("movieId")Long movieId, @RequestParam("cinemaId")Long cinemaId){
        long idByCinemaIdAndMovieId = cinemaMoviesSer.getIdByCinemaIdAndMovieId(cinemaId, movieId);
        moviePlans.setCinemaMovieId(idByCinemaIdAndMovieId);
        int insert = moviePlanMapper.insert(moviePlans);
        return moviePlans.getId();
    }


    /**
     * 获取某个电影院信息和演出计划,时间差8小时日期差一天
     * @param cinemaId
     * @param movieId
     * @return
     */
    @GetMapping("/getPlanAndCinema")
    public Map<String,Object> getCinema(@Param("movieId") Long movieId, @Param("cinemaId") Long cinemaId){
        HashMap<Date, List<MoviePlan>> dataMap = getPlan(movieId, movieId);
        //查影院
        AreaCinemas allById = areaCinemaSerImpl.getAllById(cinemaId);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        //查电影
        Movie movieById = movieSerImpl.getMovieById(movieId);

        stringObjectHashMap.put("movie",movieById);
        stringObjectHashMap.put("date",dataMap);
        stringObjectHashMap.put("cinema",allById);
        return stringObjectHashMap;
    }

    /**
     * 删除演出计划
     * @param planId
     * @return
     */
    @DeleteMapping("/delPlan")
    public int addPlan(@RequestParam("planId")Long  planId){
       return moviePlanMapper.deleteById(planId);
    }


    /**
     * 查询演出计划
     * @param movieId
     * @param cinemaId
     * @return
     */
    @GetMapping("/getPlan")
    public HashMap<Date, List<MoviePlan>> getPlan(Long movieId,Long cinemaId){
        //查计划
        List<MoviePlan> moviePlan = moviePlanSerImpl.getMoviePlan(movieId, cinemaId);
        HashMap<Date, List<MoviePlan>> dataMap = new HashMap<>();
        for (MoviePlan plan : moviePlan) {
            if(dataMap.containsKey(plan.getPlanDate())){
                dataMap.get(plan.getPlanDate()).add(plan);
            }else{
                ArrayList<MoviePlan> moviePlans = new ArrayList<>();
                moviePlans.add(plan);
                dataMap.put(plan.getPlanDate(),moviePlans);
            }
        }
        return dataMap;
    }


    /**
     * 编辑演出计划
     * @param moviePlan
     * @return
     */
    @PutMapping("/editPlan")
    public int updatePlan(@RequestBody  MoviePlan moviePlan){
       return moviePlanMapper.updateById(moviePlan);
    }


    /**
     * 设置某部电影的最低价格
     * @param cinemaId
     * @param movieId
     */
    @PostMapping("/setLowMoney")
   public int setLawMoney(@RequestParam("lowMoney")Double lowMoney,@RequestParam("cinemaId")Long cinemaId,@RequestParam("movieId") Long movieId){
      return  cinemaMoviesSer.setMovieLowMoneyByCinemaIdAndMovieId(lowMoney,cinemaId,movieId);
   }

}
