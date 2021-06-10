package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.Movie;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface MovieService {
    List<Movie> getMoviesOn();
    List<Movie> getMoviesSoon();
    List<Movie> getMoviesHot();
    Movie getMovieById(int id);
    Movie getMovieByStatus(int status,int movieId);
    List<Movie> getMovies();
    List<Movie> getHotMoviesByScore();
    List<Movie> getMoviesLikes();
    List<Movie> getOnMoviesByTime();
    List<Movie> getHotMoviesByTime();
    List<Movie> getSoonMoviesByTime();
    List<Movie> getMoviesDayMoney() ;
    List<Movie> getHotAndOnMoviesByScore();
    int addMovie(Movie movie);
    int editMovie(Movie movie);
    int  addMoney(Double money, int movieId);
    int  downMoney(Double money, int movieId);
    List<Movie> selectMovieByListId(List<Integer> listId);
    List<Movie> getOnAndSoonMovies();
    HashMap<String, List<Movie>> getMovieByShow();
    List<Movie> getMoviesOnByRedis();
    List<Movie> getMoviesSoonByRedis();
    List<Movie> getMoviesHotByRedis();
    int setHeadPicture(String head,int id);
}
