package com.qkm.TTMS.controller;

import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class MovieController {




    private final CinemaMoviesMapper cinemaMoviesMapper;
    private final CinemaMoviesService cinemaMoviesSer;
    private final MovieService movieSer;

    public MovieController(MovieService movieSer, CinemaMoviesService cinemaMoviesSer, CinemaMoviesMapper cinemaMoviesMapper) {
        this.movieSer = movieSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.cinemaMoviesMapper = cinemaMoviesMapper;

    }

    /**
     * 获取所有的电影信息
     * @return
     */
    @Cacheable(cacheNames = "movies",key = "'movieList'")
    @GetMapping("/getMovies")
    public HashMap<String, List<Movie>> getMovies(){
        List<Movie> moviesOn = movieSer.getMoviesOn();
        List<Movie> moviesSoon = movieSer.getMoviesSoon();
        List<Movie> moviesHot = movieSer.getMoviesHot();
        HashMap<String,  List<Movie>> map = new HashMap<String,  List<Movie>>();
        map.put("on",moviesOn);
        map.put("soon",moviesSoon);
        map.put("hot",moviesHot);
        return  map;
    }

    /**
     * 按评分排
     */
    @GetMapping("/ByScore")
     public List<Movie> getScore(){
        List<Movie> movies = movieSer.getMovies();
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieScore() > o2.getMovieScore()) {
                    return -1;
                } else if (o1.getMovieScore() < o2.getMovieScore()) {
                    return 1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return  movies.subList(0,9);
    }

    @GetMapping("/getSoonAndOn")
    public List<Movie> getOnAndSoonMovies(){
       return  movieSer.getOnAndSoonMovies();
    }

    /**
     * 按最受期待值排
     */
    @GetMapping("/ByLikes")
    public List<Movie> getLikes(){
        List<Movie> movies = movieSer.getMoviesLikes();
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
               if(o1.getWantLook() > o2.getWantLook()){
                   return -1;
               }else if(o1.getWantLook() < o2.getWantLook()){
                   return 1;
                }else{
                  return o1.getMovieName().compareTo(o2.getMovieName());
               }
            }
        });
        return  movies;
    }
    
    
    /**
     * 获取电影具体信息
     * @RequestParam status
     * @RequestParam movieId
     * @return
     */
    @GetMapping("/getSpecificMovie/{status}/{movieId}")
    public Movie getSpecific(@PathVariable("status") int status,@PathVariable("movieId") long movieId) {
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
    @GetMapping("/adminGetMovies/{cinemaId}")
    public List<Movie> getMovies(@PathVariable("cinemaId")Long cinemaId){
        List<Long> listByCinemaId = cinemaMoviesSer.getListMovieIdByCinemaId(cinemaId);
        return movieSer.selectMovieByListId(listByCinemaId);
    }


    /**
     * 管理员增加电影,从经理处增加
     */
    @PostMapping("/adminAddMovies/{movieId}/{cinemaId}")
    public int AdminAddMovie(@PathVariable("movieId")Long movieId, @PathVariable("cinemaId")Long cinemaId){
            CinemaMovies cinemaMovies = new CinemaMovies();
            cinemaMovies.setCinemaId(cinemaId);
            cinemaMovies.setMovieId(movieId);
            cinemaMoviesMapper.insert(cinemaMovies);
            return 1;
    }


//    /**
//     * 管理员增加电影,自己增加新的
//     */
//    @PostMapping("/adminAddMovies")
//    public int AdminAddMovie(@RequestBody Movie movie,@RequestParam("cinemaId")Long cinemaId){
//
//        CinemaMovies cinemaMovies = new CinemaMovies();
//        cinemaMovies.setCinemaId(cinemaId);
//        cinemaMovies.setMovieId(movie.getId());
//        cinemaMoviesMapper.insert(cinemaMovies);
//        return 1;
//    }



    /**
     * 管理员删除电影,只能删除自己影院的电影
     */
    @DeleteMapping("/AdminDelMovie")
    public int AdminDelMovie(@RequestParam("movieId") Long movieId,@RequestParam("cinemaId")Long cinemaId){
        cinemaMoviesSer.deleteByCinemaIdAndMovieId(cinemaId,movieId);
        return 1;
    }


}
