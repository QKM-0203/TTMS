package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.MovieMapper;
import com.qkm.TTMS.service.MovieService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    public Movie getMovieById(int id) {
        return movieMapper.getMovieByMovieId(id);
    }



    @Override
    public Movie getMovieByStatus(int status, int movieId){
        if(status == 1){
            List<Movie> moviesOnByRedis = getMoviesOnByRedis();
            return ForListMovies(moviesOnByRedis,movieId);
        }
        if (status == 2) {
            List<Movie> moviesSoonByRedis = getMoviesSoonByRedis();
            return ForListMovies(moviesSoonByRedis,movieId);
        }
        if(status == 3){
            List<Movie> moviesHotByRedis = getMoviesHotByRedis();
            return ForListMovies(moviesHotByRedis,movieId);
        }
        return null;
    }



    @Override
    public List<Movie> getMovies() {
        List<Movie> list = getMoviesHotByRedis();
        list.addAll(getMoviesOnByRedis());
        list.addAll(getMoviesSoonByRedis());
        return list;
    }


    public List<Movie> getHotMoviesByScore(){
        List<Movie> moviesHotByRedis = getMoviesHotByRedis();
        Collections.sort(moviesHotByRedis, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieScore() > o2.getMovieScore()) {
                    return 1;
                } else if (o1.getMovieScore() < o2.getMovieScore()) {
                    return -1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return moviesHotByRedis;
    }


    public List<Movie> getOnMoviesByTime(){
        List<Movie> moviesOnByRedis = getMoviesOnByRedis();
        Collections.sort(moviesOnByRedis, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0 ) {
                    return 1;
                } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                    return -1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return moviesOnByRedis;
    }

    @Override
    public List<Movie> getHotMoviesByTime() {
        List<Movie> moviesHotByRedis = getMoviesHotByRedis();
        Collections.sort(moviesHotByRedis, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0 ) {
                    return 1;
                } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                    return -1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return moviesHotByRedis;
    }

    @Override
    public List<Movie> getSoonMoviesByTime() {
        List<Movie> moviesSoonByRedis = getMoviesSoonByRedis();
        Collections.sort(moviesSoonByRedis, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0 ) {
                    return 1;
                } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                    return -1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return moviesSoonByRedis;
    }

    public List<Movie> getHotAndOnMoviesByScore(){
        List<Movie> movies = getMoviesHotByRedis();
        movies.addAll(getMoviesOnByRedis());
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieScore() > o2.getMovieScore()) {
                    return 1;
                } else if (o1.getMovieScore() < o2.getMovieScore()) {
                    return -1;
                } else {
                    //如果当天的钱数相同就按电影名字名字进行排序
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return movies;
    }




    @Override
    public List<Movie> getMoviesLikes() {
        List<Movie> moviesSoonByRedis = getMoviesSoonByRedis();
        Collections.sort(moviesSoonByRedis, new Comparator<Movie>() {
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
        return moviesSoonByRedis;
    }


    @Override
    public List<Movie> getMoviesDayMoney() {
        List<Movie> moviesOnByRedis = getMoviesOnByRedis();
        Collections.sort(moviesOnByRedis ,new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.getDayMoney() > o2.getDayMoney()){
                    return -1;
                }else if(o1.getDayMoney() < o2.getDayMoney()){
                    return 1;
                }else{
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        return moviesOnByRedis;
    }

    @Override
    public int addMovie(Movie movie) {
            int insert = movieMapper.insert(movie);
            HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
            if(movieMap != null){
                if(movie.getMovieStatus() == 2) {
                    movieMap.get("soon").add(movie);

                }else{
                    movieMap.get("on").add(movie);

                }
                redisTemplate.opsForValue().set("movies::movieList",movieMap);
            }
            return insert;

    }


    @Override
    public int editMovie(Movie movie) {
        //try {
            movieMapper.updateById(movie);
            redisTemplate.delete("movies::movieList");
            List<Movie> moviesOn = getMoviesOn();
            List<Movie> moviesSoon = getMoviesSoon();
            List<Movie> moviesHot = getMoviesHot();
            HashMap<String,  List<Movie>> map = new HashMap<>();
            map.put("on",moviesOn);
            map.put("soon",moviesSoon);
            map.put("hot",moviesHot);
            redisTemplate.opsForValue().set("movies::movieList",map);
            return 1;
        //}catch (Exception e){
        //    return 0;
       // }
    }

    @Override
    public int addMoney(Double money, int movieId) {
        return  movieMapper.addMoney(money, movieId);

    }

    @Override
    public int downMoney(Double money, int movieId) {
        return movieMapper.downMoney(money,movieId);
    }

    @Override
    public List<Movie> selectMovieByListId(List<Integer> listId) {
        return movieMapper.selectMovieByListId(listId);
    }

    @Override
    public List<Movie> getOnAndSoonMovies() {
        List<Movie> on = getMoviesOnByRedis();
        on.addAll(getMoviesSoonByRedis());
        return  on;
    }

    @Override
    public HashMap<String, List<Movie>> getMovieByShow() {
        HashMap<String,  List<Movie>> map = new HashMap<>();
        List<Movie> moviesOnByRedis = getMoviesOnByRedis();
        moviesOnByRedis.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0) {
                    return -1;
                } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                    return 1;
                } else {
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        map.put("on",getMoviesOnByRedis().subList(0,9));
        List<Movie> moviesSoonByRedis = getMoviesSoonByRedis();
        moviesSoonByRedis.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getMovieStart().compareTo(o2.getMovieStart()) > 0) {
                    return 1;
                } else if (o1.getMovieStart().compareTo(o2.getMovieStart()) < 0) {
                    return -1;
                } else {
                    return o1.getMovieName().compareTo(o2.getMovieName());
                }
            }
        });
        map.put("soon",getMoviesSoonByRedis().subList(0,9));
        map.put("hot",getMoviesHotByRedis().subList(0,9));
        return map;
    }

    @Override
    public List<Movie> getMoviesOnByRedis() {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");
        if(movieMap == null){
            return getMoviesOn();
        }
        return movieMap.get("on");
    }

    @Override
    public List<Movie> getMoviesSoonByRedis() {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");

        if(movieMap == null){
            return getMoviesSoon();
        }
        return movieMap.get("soon");
    }

    @Override
    public List<Movie> getMoviesHotByRedis() {
        HashMap<String, List<Movie>> movieMap = (HashMap<String, List<Movie>>) redisTemplate.opsForValue().get("movies::movieList");

        if(movieMap == null){
            return getMoviesHot();
        }
        return movieMap.get("hot");
    }


    public  Movie ForListMovies(List<Movie> Movie,int movieId) {
        for (Movie movie : Movie) {
            if (movie.getId().equals(movieId)) {
                return movie;
            }
        }
        return  null;
    }


}
