package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MovieSer {
    List<Movie> getMoviesOn();
    List<Movie> getMoviesSoon();
    List<Movie> getMoviesHot();
    Movie getMovieById(Long id);
    Movie getMovieByStatus(int status,long movieId);
    List<Movie> getMovies();
    List<Movie> getMoviesLikes();
   int addMovie(Movie movie);
   int editMovie(Movie movie,long cinemaId);
   int  updateMoney(Long money, String movieName);

}
