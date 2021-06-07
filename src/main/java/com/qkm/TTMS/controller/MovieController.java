package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.CommonService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
public class MovieController {




    private final CommonService commonService;
    private final CinemaMoviesMapper cinemaMoviesMapper;
    private final CinemaMoviesService cinemaMoviesSer;
    private final MovieService movieSer;

    public MovieController(MovieService movieSer, CinemaMoviesService cinemaMoviesSer, CinemaMoviesMapper cinemaMoviesMapper, CommonService commonService) {
        this.movieSer = movieSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.cinemaMoviesMapper = cinemaMoviesMapper;

        this.commonService = commonService;
    }

    /**
     * 获取所有的电影信息，加入缓存
     * @return  所有的电影信息
     */
    @Cacheable(cacheNames = "movies",key = "'movieList'")
    @GetMapping("/getMovies")
    public HashMap<String, List<Movie>> getAllMovies(){
        List<Movie> moviesOn = movieSer.getMoviesOn();
        List<Movie> moviesSoon = movieSer.getMoviesSoon();
        List<Movie> moviesHot = movieSer.getMoviesHot();
        HashMap<String,  List<Movie>> map = new HashMap<>();
        map.put("on",moviesOn);
        map.put("soon",moviesSoon);
        map.put("hot",moviesHot);
        return  map;
    }





    /**
     * 获取首页的电影信息
     * @return  所有的电影信息
     */
    @GetMapping("/getMoviesByShow")
    public HashMap<String, List<Movie>> getMoviesByShow(){
        HashMap<String, List<Movie>> movieByShow = movieSer.getMovieByShow();
        List<Movie> score = getScore();
        movieByShow.put("top100",score.subList(0,9));
        List<Movie> likes = getLikes();
        movieByShow.put("like",likes.subList(0,9));
        List<Movie> byDayMoney = getByDayMoney();
        movieByShow.put("dayMoney",byDayMoney.subList(0,5));
        return  movieByShow;
    }


    /**
     * 按评分排,排序热播的
     */
    @GetMapping("/ByScore")
     public List<Movie> getScore(){
        return movieSer.getHotMoviesByScore();
    }

    /**
     * 按评分排,排序热播和正在上映的分页
     */
    @GetMapping("/ByScoreByOnAndHotPage/{page}")
    public List<Movie> getScore(@PathVariable("page")int page){
        List<Movie> hotAndOnMoviesByScore = movieSer.getHotAndOnMoviesByScore();
        List<Movie> movieList = (List<Movie>) commonService.getPage(hotAndOnMoviesByScore,page,10);
        movieList.add(new Movie(commonService.justPage(hotAndOnMoviesByScore,10)));
        return movieList;
    }

    /**
     * 按评分排,排序热播的分页
     */
    @GetMapping("/ByScoreByPage/{page}")
    public List<Movie> getScoreByPage(@PathVariable("page")int page){
        List<Movie> score = getScore();
        List<Movie> movieList = (List<Movie>) commonService.getPage(score,page,10);
        movieList.add(new Movie(commonService.justPage(score,10)));
        return movieList;
    }




    /**
     * 即将上映按最受期待值排
     */
    @GetMapping("/ByLikes")
    public List<Movie> getLikes(){
        return movieSer.getMoviesLikes();
    }

    /**
     * 即将上映按最受期待值排分页
     */
    @GetMapping("/ByLikesPage/{page}")
    public List<Movie> getLikesByPage(@PathVariable("page")int page){
        List<Movie> likes = getLikes();
        List<Movie> movieList = (List<Movie>) (List<Movie>) commonService.getPage(likes,page,10);
        movieList.add(new Movie(commonService.justPage(likes,10)));
        return movieList;
    }



    /**
     * 按正在上映的每天收益排
     */
    @GetMapping("/ByDayMoney")
    public List<Movie> getByDayMoney(){
        return movieSer.getMoviesDayMoney();
    }



    /**
     * 按正在上映的每天收益排分页
     */
    @GetMapping("/ByDayMoney/{page}")
    public List<Movie> getByDayMoneyPage(@PathVariable("page")int page){
        List<Movie> moviesDayMoney = movieSer.getMoviesDayMoney();
        List<Movie> movieList = (List<Movie>) commonService.getPage(moviesDayMoney,page,10);
        movieList.add(new Movie(commonService.justPage(moviesDayMoney,10)));
        return movieList;
    }





    /**
     * 获取电影具体信息
     * @RequestParam status  电影的状态
     * @RequestParam movieId  电影的Id
     * @return   返回电影的具体信息
     */
    @GetMapping("/getSpecificMovie/{status}/{movieId}")
    public Movie getSpecific(@PathVariable("status") int status,@PathVariable("movieId") int movieId) {
        return movieSer.getMovieByStatus(status, movieId);
    }


    /**
     * 经理增加电影
     *
     */
    @PostMapping("/ManageAddMovie")
    public int ManageAddMovie(@RequestBody Movie movie) {
        return movieSer.addMovie(movie);
    }


    /**
     * 经理编辑电影,例如将上映电影改成热播电影,或者将即将上映改成正在上映电影
     */
    @PutMapping("/editMovie")
    public int editMovie(@RequestBody Movie movie) {
        return movieSer.editMovie(movie);
    }


    /**
     * 经理获取电影院的所有电影
     */
     @GetMapping("/AdminGetMovies")
     public List<Movie> AdminGetMovies(){
         return movieSer.getMovies();
     }




    /**
     * 管理员获取电影院所有的上映和即将上映电影
     */
    @GetMapping("/adminGetMovies/{cinemaId}/{page}")
    public List<Movie> getMovies(@PathVariable("cinemaId")int cinemaId,@PathVariable("page")int page){
        return cinemaMoviesSer.getListMovieIdByCinemaId(cinemaId,page);
    }


    /**
     * 管理员增加电影,从库里面增加电影院没有的
     */
    @PostMapping("/adminGetMovieByLibrary/{cinemaId}")
    public Set<Movie> AdminGetMovieByLibrary(@PathVariable("cinemaId")int cinemaId){
        List<Movie> onAndSoonMovies = movieSer.getOnAndSoonMovies();
        List<Movie> movies = getMovies(cinemaId,-1);
        System.out.println(onAndSoonMovies);
        System.out.println(movies);
        Set<Movie> movieSet = new HashSet<>();
        movieSet.addAll(onAndSoonMovies);
        movieSet.removeAll(movies);
        System.out.println(movieSet);
        return  movieSet;
    }


    /**
     * 管理员增加电影,从经理处(库里面)增加
     */
    @PostMapping("/adminAddMovies")
    public int adminAddMovie(@RequestBody List<CinemaMovies> cinemaMovieIdList){
        for (CinemaMovies cinemaMovies : cinemaMovieIdList) {
            cinemaMoviesMapper.insert(cinemaMovies);
        }
        return 1;
    }




    /**
     * 管理员删除电影,只能删除自己影院的电影
     */
    @DeleteMapping("/AdminDelMovie/{movieId}/{cinemaId}")
    public int AdminDelMovie(@PathVariable("movieId") int movieId,@PathVariable("cinemaId")int cinemaId){
        cinemaMoviesSer.deleteByCinemaIdAndMovieId(cinemaId,movieId);
        return 1;
    }

//
//    /**
//     * 按时间排序
//     * @param status
//     * @param page
//     * @return
//     */
//    @GetMapping("/SortByTime/{status}/{page}")
//    public List<Movie> SortByTime(@PathVariable("status")int status,@PathVariable("page")int page){
//        if(status == 1){
//           List<Movie> movieList = (List<Movie>) commonService.getPage(movieSer.getOnMoviesByTime(),page,12);
//           movieList.add(new Movie(movieList.size()/12));
//           return movieList;
//       }else if(status == 2){
//           List<Movie> movieList = (List<Movie>) commonService.getPage(movieSer.getSoonMoviesByTime(),page,12);
//           movieList.add(new Movie(movieList.size()/12));
//           return movieList;
//       }else{
//           List<Movie> movieList = (List<Movie>) commonService.getPage(movieSer.getHotMoviesByTime(),page,12);
//           movieList.add(new Movie(movieList.size()/12));
//           return movieList;
//       }
//    }


}
