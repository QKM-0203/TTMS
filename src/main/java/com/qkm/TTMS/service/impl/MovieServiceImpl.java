package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.CinemaMoviesMapper;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.mapper.MovieMapper;
import com.qkm.TTMS.service.AreaCinemaService;
import com.qkm.TTMS.service.CinemaMoviesService;
import com.qkm.TTMS.service.MovieService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieServiceImpl implements MovieService {

    private final RedisTemplate<String,Object>  redisTemplate;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieMapper movieMapper, RedisTemplate<String, Object> redisTemplate) {
        this.movieMapper = movieMapper;
        this.redisTemplate = redisTemplate;

    }

    @Override
    public List<Movie> getMoviesOn() {
        return movieMapper.getMoviesOn();
    }

    @Override
    public List<Movie> getMoviesSoon() {
        return movieMapper.getMoviesSoon();
    }

    @Override
    public List<Movie> getMoviesHot() {
        return movieMapper.getMoviesHot();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieMapper.getMovieByMovieId(id);
    }



    @Override
    public Movie getMovieByStatus(int status, long movieId){
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");

        if (movieMap == null) {
            System.out.println(1);
            return getMovieById(movieId);
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


    @Override
    public List<Movie> getMovies(){
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
        List<Movie> on = movieMap.get("on");
        on.addAll(movieMap.get("soon"));
        on.addAll(movieMap.get("hot"));
        return  on;
    }



    @Override
    public List<Movie> getMoviesLikes() {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
        return movieMap.get("soon");
    }

    @Override
    public int addMovie(Movie movie) {
            int insert = movieMapper.insert(movie);
            HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
            if(movieMap != null){
                if(movie.getMovieStatus() == 2) {
                    movieMap.get("soon").add(movie);
                }else if(movie.getMovieStatus() == 3){
                    movieMap.get("hot").add(movie);
                }
                redisTemplate.opsForValue().set("movies::movieList",movieMap);
            }
            return insert;

    }


    @Override
    public int editMovie(Movie movie) {
        movieMapper.updateById(movie);
        redisTemplate.delete("movies::movieList");
        return  1;
    }

    @Override
    public int addMoney(Long money, Long movieId) {
        return  movieMapper.addMoney(money, movieId);

    }

    @Override
    public int downMoney(Long money, Long movieId) {
        return movieMapper.downMoney(money,movieId);
    }

    @Override
    public List<Movie> selectMovieByListId(List<Long> listId) {
        return movieMapper.selectMovieByListId(listId);
    }

    @Override
    public List<Movie> getOnAndSoonMovies() {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
        List<Movie> on = movieMap.get("on");
        on.addAll(movieMap.get("soon"));
        return  on;
    }


}
