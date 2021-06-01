package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.CinemaMovies;

import java.util.List;

public interface CinemaMoviesService {
    int getIdByCinemaIdAndMovieId(int cinemaId,int movieId);
    int deleteByCinemaIdAndMovieId(int cinemaId, int movieId);
    int  deleteByMovieId(int movieId);
    List<Integer> getListMovieIdByCinemaId(int cinemaId);
    int setMovieLowMoneyByCinemaIdAndMovieId(Double movieLowMoney,int cinemaId,int movieId);
    CinemaMovies getAllByCinemaId( int cinemaId);
    int deleteByCinemaId( int cinemaId);
}
