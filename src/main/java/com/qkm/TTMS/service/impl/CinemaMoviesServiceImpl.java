package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.CinemaMovies;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.entity.UserOrder;
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
    public int addMoney(Double money, int id) {
        return cinemaMoviesMapper.addMoney(money,id);
    }

    @Override
    public int downMoney(Double money, int id) {
        return cinemaMoviesMapper.downMoney(money,id);
    }

    @Override
    public int getIdByCinemaIdAndMovieId(int cinemaId, int movieId) {
        return  cinemaMoviesMapper.getIdByCinemaIdAndMovieId(cinemaId,movieId);
    }

    @Override
    public int deleteByCinemaIdAndMovieId(int cinemaId, int movieId) {
        return cinemaMoviesMapper.deleteByCinemaIdAndMovieId(cinemaId, movieId);
    }

    @Override
    public int deleteByMovieId(int movieId) {
        return cinemaMoviesMapper.deleteByMovieId(movieId);
    }


    @Override
    public List<Movie> getListMovieIdByCinemaId(int cinemaId,int page) {
        if(page == -1){
            return cinemaMoviesMapper.getListMovieIdByCinemaIdNotPage(cinemaId);
        }
        Page<Movie> moviePage = new Page<>(page, 20, true);
        IPage<Movie> listMovieIdByCinemaId = cinemaMoviesMapper.getListMovieIdByCinemaId(moviePage, cinemaId);
        List<Movie> listMovieIdByCinemaIdList = listMovieIdByCinemaId.getRecords();
        if(listMovieIdByCinemaIdList.size() == 0){
            return null;
        }
        listMovieIdByCinemaIdList.add(new Movie(String.valueOf(listMovieIdByCinemaId.getPages())));
        return listMovieIdByCinemaIdList;

    }

    @Override
    public int setMovieLowMoneyByCinemaIdAndMovieId(Double movieLowMoney, int cinemaId, int movieId) {
        return cinemaMoviesMapper.setMovieLowMoneyByCinemaIdAndMovieId(movieLowMoney, cinemaId, movieId);
    }

    @Override
    public CinemaMovies getAllByCinemaId(int cinemaId) {
        return cinemaMoviesMapper.getAllByCinemaId(cinemaId);
    }

    @Override
    public int deleteByCinemaId(int cinemaId) {
        return cinemaMoviesMapper.deleteByCinemaId(cinemaId);
    }

  }
