package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.CinemaMoviesMapper;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.mapper.MovieMapper;
import com.qkm.TTMS.service.MovieSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieSerImpl implements MovieSer {

    @Autowired
    private  HallSeatMapper hallSeatMapper;
    private final MoviePlanSerImpl moviePlanSer;
    private final RedisTemplate<String,Object>  redisTemplate;
    private final MovieMapper movieMapper;
    private final CinemaMoviesSerImpl cinemaMoviesSer;
    private final AreaCinemaSerImpl areaCinemaSer;
    private final CinemaMoviesMapper cinemaMoviesMapper;

    public MovieSerImpl(MovieMapper movieMapper, RedisTemplate<String, Object> redisTemplate, CinemaMoviesSerImpl cinemaMoviesSer, MoviePlanSerImpl moviePlanSer, AreaCinemaSerImpl areaCinemaSer, CinemaMoviesMapper cinemaMoviesMapper) {
        this.movieMapper = movieMapper;
        this.redisTemplate = redisTemplate;
        this.cinemaMoviesSer = cinemaMoviesSer;
        this.moviePlanSer = moviePlanSer;
        this.areaCinemaSer = areaCinemaSer;
        this.cinemaMoviesMapper = cinemaMoviesMapper;
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
    public List<Movie> getOnAndSoonMovies(){
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
        List<Movie> on = movieMap.get("on");
        on.addAll(movieMap.get("soon"));
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
            List<Long> listID = areaCinemaSer.getListID();
            CinemaMovies cinemaMovies = new CinemaMovies();
            for (Long aLong : listID) {
                cinemaMovies.setCinemaId(aLong);
                cinemaMovies.setMovieId(movie.getId());
                cinemaMoviesMapper.insert(cinemaMovies);
            }
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
    public int editMovie(Movie movie,long cinemaId) {
        Movie movieByMovieId = movieMapper.getMovieByMovieId(movie.getId());
        if(movie.getMovieStatus() == 3 && movieByMovieId.getMovieStatus().equals(1)){
             areaCinemaSer.delCinema(cinemaId,movie.getId());
             redisTemplate.delete("movies::movieList");
             return  1;
        }else{
            redisTemplate.delete("movies::movieList");
            return movieMapper.updateById(movie);
        }

    }

    @Override
    public int updateMoney(Long money, String movieName) {
        return  movieMapper.updateMoney(money, movieName);



    }

    @Override
    public List<Movie> selectMovieByListId(List<Long> listId) {
        return movieMapper.selectMovieByListId(listId);
    }


}
