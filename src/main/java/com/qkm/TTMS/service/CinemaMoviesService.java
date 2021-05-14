package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.CinemaMovies;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CinemaMoviesService {
    long getIdByCinemaIdAndMovieId(Long cinemaId,Long movieId);
    int deleteByCinemaIdAndMovieId(Long cinemaId,Long movieId);
    int  deleteByMovieId(Long movieId);
    List<Long> getListMovieIdByCinemaId(Long cinemaId);
    int setMovieLowMoneyByCinemaIdAndMovieId(Double movieLowMoney,Long cinemaId,Long movieId);
    CinemaMovies getAllByCinemaId( Long cinemaId);
    int deleteByCinemaId( Long cinemaId);
}