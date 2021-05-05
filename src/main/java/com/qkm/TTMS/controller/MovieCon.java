package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.config.RedisConfig;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.MovieVideo;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.impl.CinemaMoviesSerImpl;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@RestController
public class MovieCon {




    private final MovieMapper movieMapper;
    private final CinemaMoviesMapper cinemaMoviesMapper;
    private final CinemaMoviesSerImpl cinemaMoviesSer;
    private final MovieSerImpl movieSerImpl;

    public MovieCon(MovieSerImpl movieSerImpl, CinemaMoviesSerImpl cinemaMoviesSer, MovieMapper movieMapper, CinemaMoviesMapper cinemaMoviesMapper) {
        this.movieSerImpl = movieSerImpl;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.movieMapper = movieMapper;
        this.cinemaMoviesMapper = cinemaMoviesMapper;
    }

    /**
     * 获取所有的电影信息
     * @return
     */
    @Cacheable(cacheNames = "movies",key = "'movieList'")
    @GetMapping("/getMovies")
    public HashMap<String, List<Movie>> getMovies(){
        List<Movie> moviesOn = movieSerImpl.getMoviesOn();
        List<Movie> moviesSoon = movieSerImpl.getMoviesSoon();
        List<Movie> moviesHot = movieSerImpl.getMoviesHot();
        HashMap<String,  List<Movie>> map = new HashMap<String,  List<Movie>>();
        map.put("on",moviesOn);
        map.put("soon",moviesSoon);
        map.put("hot",moviesHot);
//        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
//        assert jedisConnectionFactory != null;
//        jedisConnectionFactory.setDatabase(2);
//        jedisConnectionFactory.setShareNativeConnection(false);
//        jedisConnectionFactory.resetConnection();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return  map;
    }

    /**
     * 按评分排
     */
    @GetMapping("/ByScore")
     public List<Movie> getScore(){
        List<Movie> movies = movieSerImpl.getMovies();
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
        return  movies.subList(0,101);
    }

    /**
     * 按最受期待值排
     */
    @GetMapping("/ByLikes")
    public List<Movie> getLikes(){
        List<Movie> movies = movieSerImpl.getMoviesLikes();
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
     * @param status
     * @param movieId
     * @return
     */
    @GetMapping("/getSpecificMovie")
    public Movie getSpecific(@Param("status") int status,@Param("movieId") long movieId) {
        return movieSerImpl.getMovieByStatus(status, movieId);
    }


    /**
     * 经理增加电影
     *
     */
    @PostMapping("/ManageAddMovie")
    public int ManageAddMovie(@RequestBody Movie movie) {
        return movieSerImpl.addMovie(movie);
    }


    /**
     * 经理编辑电影,例如将上映电影改成热播电影,或者将即将上映改成正在上映电影
     */
    @PutMapping("/editMovie")
    public int editMovie(@RequestBody Movie movie,@RequestParam("cinemaId")long cinemaId) {
        return movieSerImpl.editMovie(movie,cinemaId);
    }

    /**
     * 经理获取电影院的所有电影
     */
     @GetMapping("/AdminGetMovies")
     public List<Movie> AdminGetMovies(){
         return movieSerImpl.getMovies();
     }

//    /**
//     * 经理删除电影,所有影院都没有这个电影
//     */
//    @GetMapping("/AdminDelMovies")
//    public int AdminDelMovies(@RequestParam("movieId") Long movieId){
//        cinemaMoviesMapper.deleteByMovieId(movieId);
//        return movieMapper.deleteById(movieId);
//    }


    /**
     * 管理员获取电影院所有的上映和即将上映电影
     */
    @GetMapping("/adminGetMovies")
    public List<Movie> getMovies(@RequestParam("movieId")Long movieId,@RequestParam("cinemaId")Long cinemaId){
        List<Long> listByCinemaId = cinemaMoviesSer.getListMovieIdByCinemaId(cinemaId);
        return movieMapper.selectBatchIds(listByCinemaId);
    }


    /**
     * 管理员增加电影
     */
    @PostMapping("/adminAddMovies")
    public int AdminAddMovie(@RequestBody Movie movie,@RequestParam("cinemaId")Long cinemaId){
        try {
            movieSerImpl.addMovie(movie);
            CinemaMovies cinemaMovies = new CinemaMovies();
            cinemaMovies.setCinemaId(cinemaId);
            cinemaMovies.setMovieId(movie.getId());
            cinemaMoviesMapper.insert(cinemaMovies);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    /**
     * 管理员删除电影,只能删除自己影院的电影
     */
    @DeleteMapping("/AdminDelMovie")
    public int AdminDelMovie(@RequestParam("movieId") Long movieId,@RequestParam("cinemaId")Long cinemaId){
        movieMapper.deleteById(movieId);
        return cinemaMoviesSer.deleteByCinemaIdAndMovieId(cinemaId,movieId);
    }

//    /**
//     * 管理员编辑电电影,只能编辑自己影院添加的电影
//     */





}
