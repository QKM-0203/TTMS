package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.entity.MoviePlan;
import com.qkm.TTMS.mapper.MovieHallMapper;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.MoviePlanService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PlanController {


    private final RedisTemplate<String,Object> redisTemplate;
    private final MovieHallMapper movieHallMapper;
    private final MoviePlanMapper moviePlanMapper;
    private final CinemaMoviesService cinemaMoviesSer;
    private final AreaCinemaService areaCinemaSer;
    private  final MovieService movieSer;
    private  final MoviePlanService moviePlanSer;


    public PlanController(MovieService movieSer, MoviePlanService moviePlanSer, AreaCinemaService areaCinemaSer, CinemaMoviesService cinemaMoviesSer, MoviePlanMapper moviePlanMapper, RedisTemplate<String, Object> redisTemplate, MovieHallMapper movieHallMapper) {
        this.movieSer = movieSer;
        this.moviePlanSer = moviePlanSer;
        this.areaCinemaSer = areaCinemaSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.moviePlanMapper = moviePlanMapper;
        this.redisTemplate = redisTemplate;
        this.movieHallMapper = movieHallMapper;
    }


    /**
     * 增加演出计划
     * @param moviePlans 计划信息
     * @return  是否增加成功
     */
    @PostMapping("/addPlan/{movieId}/{cinemaId}")
    public int addPlan(@RequestBody MoviePlan moviePlans, @PathVariable("movieId")int movieId, @PathVariable("cinemaId")int cinemaId){
        int idByCinemaIdAndMovieId = cinemaMoviesSer.getIdByCinemaIdAndMovieId(cinemaId, movieId);
        moviePlans.setCinemaMovieId(idByCinemaIdAndMovieId);
        moviePlanMapper.insert(moviePlans);
        MovieHall movieHall = movieHallMapper.selectById(moviePlans.getHallId());
        HashMap<String, String> stringMap = new HashMap<>();
        for(int i = 1; i <= movieHall.getSeatLine(); i++ ){
            for(int j = 1; j <= movieHall.getSeatColumn(); j++){
                stringMap.put(i +","+String.valueOf(j),"0");
            }
        }
        stringMap.put("sum",String.valueOf(movieHall.getSeatLine())+","+movieHall.getSeatColumn());
        redisTemplate.opsForValue().set(String.valueOf(moviePlans.getId()),stringMap);
        return moviePlans.getId();
    }






    /**
     * 获取某个电影院信息和演出计划,时间差8小时日期差一天
     * @param cinemaId  电影院ID
     * @param movieId 电影ID
     * @return  返回演出计划
     */
    @GetMapping("/getPlanAndCinema/{movieId}/{cinemaId}")
    public Map<String,Object> getCinemaAndPlanAndMovie(@PathVariable("movieId") int movieId, @PathVariable("cinemaId") int cinemaId){
        HashMap<Date, List<MoviePlan>> dataMap = moviePlanSer.ClassficationDate(moviePlanSer.getMoviePlanByDate(movieId, cinemaId));
        //查影院
        AreaCinemas allById = areaCinemaSer.getAllById(cinemaId);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        //查电影
        Movie movieById = movieSer.getMovieById(movieId);

        stringObjectHashMap.put("movie",movieById);
        stringObjectHashMap.put("plan",dataMap);
        stringObjectHashMap.put("cinema",allById);
        return stringObjectHashMap;
    }

    /**
     * 删除演出计划
     * @param planId  计划Id
     * @return 是否删除成功
     */
    @DeleteMapping("/delPlan/{planId}")
    public int addPlan(@PathVariable("planId")int  planId){
       moviePlanMapper.deleteById(planId);
       redisTemplate.delete(String.valueOf(planId));
       return 1;
    }


    /**
     * 查询演出计划,前端过滤删除演出厅的
     * @param movieId   电影Id
     * @param cinemaId  电影院Id
     * @return  返回查询演出计划
     */
    @GetMapping("/getPlan/{movieId}/{cinemaId}")
    public HashMap<Date, List<MoviePlan>> getPlan(@PathVariable("movieId") int movieId,@PathVariable("cinemaId") int cinemaId){
        //查计划
        List<MoviePlan> moviePlan = moviePlanSer.getMoviePlan(movieId, cinemaId);
        HashMap<Date, List<MoviePlan>> dateListHashMap = moviePlanSer.ClassficationDate(moviePlan);
        return dateListHashMap;
    }


    /**
     * 编辑演出计划
     * @param moviePlan   计划信息
     * @return  是否更新成功
     */
    @PutMapping("/editPlan")
    public int updatePlan(@RequestBody  MoviePlan moviePlan){
       return moviePlanMapper.updateById(moviePlan);
    }


    /**
     * 设置某部电影的最低价格
     * @param cinemaId   影院Id
     * @param movieId  电影Id
     */
    @PostMapping("/setLowMoney/{lowMoney}/{cinemaId}/{movieId}")
   public int setLawMoney(@PathVariable("lowMoney")Double lowMoney,@PathVariable("cinemaId")int cinemaId,@PathVariable("movieId") int movieId){
      return  cinemaMoviesSer.setMovieLowMoneyByCinemaIdAndMovieId(lowMoney,cinemaId,movieId);
   }


}
