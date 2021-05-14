package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.mapper.CinemaMoviesMapper;
import com.qkm.TTMS.service.CinemaMoviesService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaMoviesServiceImpl implements CinemaMoviesService {

    private final CinemaMoviesMapper cinemaMoviesMapper;

    public CinemaMoviesServiceImpl(CinemaMoviesMapper cinemaMoviesMapper) {
        this.cinemaMoviesMapper = cinemaMoviesMapper;
    }


    @Override
    public long getIdByCinemaIdAndMovieId(Long cinemaId, Long movieId) {
        return  cinemaMoviesMapper.getIdByCinemaIdAndMovieId(cinemaId,movieId);
    }

    @Override
    public int deleteByCinemaIdAndMovieId(Long cinemaId, Long movieId) {
        return cinemaMoviesMapper.deleteByCinemaIdAndMovieId(cinemaId,movieId);
    }

    @Override
    public int deleteByMovieId(Long movieId) {
        return cinemaMoviesMapper.deleteByMovieId(movieId);
    }


    @Override
    public List<Long> getListMovieIdByCinemaId(Long cinemaId) {
        return cinemaMoviesMapper.getListMovieIdByCinemaId(cinemaId);
    }

    @Override
    public int setMovieLowMoneyByCinemaIdAndMovieId(Double movieLowMoney, Long cinemaId, Long movieId) {
        return cinemaMoviesMapper.setMovieLowMoneyByCinemaIdAndMovieId(movieLowMoney, cinemaId, movieId);
    }

    @Override
    public CinemaMovies getAllByCinemaId(Long cinemaId) {
        return cinemaMoviesMapper.getAllByCinemaId(cinemaId);
    }

    @Override
    public int deleteByCinemaId(Long cinemaId) {
        return cinemaMoviesMapper.deleteByCinemaId(cinemaId);
    }

  }
