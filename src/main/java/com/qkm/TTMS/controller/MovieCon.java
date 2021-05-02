package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MovieCon {

    private final MovieSerImpl movieSerImpl;

    public MovieCon(MovieSerImpl movieSerImpl, RedisTemplate<String, Object> redisTemplate) {
        this.movieSerImpl = movieSerImpl;
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate<String,Object> redisTemplate;

    /**
     * 获取所有的电影信息
     * @return
     */
    @Cacheable(cacheNames = "comment",key = "'movieList'")
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

        redisTemplate.opsForValue().set("movieList",map);
        return  map;
    }

    /**
     * 获取电影具体信息
     * @param status
     * @param movieId
     * @return
     */
    @GetMapping("/getSpecific")
    public Movie getSpecific(@Param("status") int status,@Param("movieId") long movieId) {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movieList");
        if (movieMap == null) {
            System.out.println(1);
         return movieSerImpl.getMovieById(movieId);
        }
        if (status == 1) {
            List<Movie> on = movieMap.get("on");
            for (Movie movie : on) {
                if (movie.getId().equals(movieId)) {
                    return movie;
                }
            }
        } else if (status == 2) {
            List<Movie> soon = movieMap.get("soon");
            for (Movie movie : soon) {
                if (movie.getId().equals(movieId)) {
                    return movie;
                }
            }
        } else {
            List<Movie> hot = movieMap.get("hot");
            for (Movie movie : hot) {
                if (movie.getId().equals(movieId)) {
                    return movie;
                }
            }
        }
        return null;
    }

}
