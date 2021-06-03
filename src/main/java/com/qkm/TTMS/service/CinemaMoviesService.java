package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;

import java.util.List;

public interface CinemaMoviesService {
    int getIdByCinemaIdAndMovieId(int cinemaId,int movieId);
    int deleteByCinemaIdAndMovieId(int cinemaId, int movieId);
    int  deleteByMovieId(int movieId);
    List<Movie> getListMovieIdByCinemaId(int cinemaId, int page);
    int setMovieLowMoneyByCinemaIdAndMovieId(Double movieLowMoney,int cinemaId,int movieId);
    CinemaMovies getAllByCinemaId( int cinemaId);
    int deleteByCinemaId( int cinemaId);
}
