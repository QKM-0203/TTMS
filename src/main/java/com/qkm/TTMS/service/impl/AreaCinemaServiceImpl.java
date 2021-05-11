package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AreaCinemaServiceImpl implements AreaCinemaService {


    private final HallSeatMapper hallSeatMapper;
    private final MoviePlanServiceImpl moviePlanSer;
    private final CinemaMoviesServiceImpl cinemaMoviesSer;
    private final AreaCinemasMapper areaCinemasMapper;

    public AreaCinemaServiceImpl(AreaCinemasMapper areaCinemasMapper, HallSeatMapper hallSeatMapper, MoviePlanServiceImpl moviePlanSer, CinemaMoviesServiceImpl cinemaMoviesSer) {
        this.areaCinemasMapper = areaCinemasMapper;

        this.hallSeatMapper = hallSeatMapper;
        this.moviePlanSer = moviePlanSer;
        this.cinemaMoviesSer = cinemaMoviesSer;
    }
    @Override
    public List<AreaCinemas> getCinemaMoviesByCinemaId(String areaName, Long movieId) {
        return areaCinemasMapper.getCinemaMoviesByCinemaId(areaName,movieId);
    }

    @Override
    public AreaCinemas getAllById(Long id) {
        return areaCinemasMapper.getAllById(id);
    }

    @Override
    public List<Long> getListID() {
        return areaCinemasMapper.getListID();
    }

    @Override
    public List<AreaCinemas> getAll() {
        return areaCinemasMapper.getAll();
    }

    @Override
    public int deleteById(Long cinemaId) {
        return areaCinemasMapper.deleteById(cinemaId);
    }

    @Override
    public int addMoney(Long money, Long cinemaId) {

            return areaCinemasMapper.addMoney(money, cinemaId);


    }

    @Override
    public int downMoney(Long money, Long cinemaId) {
        return areaCinemasMapper.downMoney(money, cinemaId);
    }

    @Override
    public List<AreaCinemas> getAllByAreaName(String cinemaName) {
        return areaCinemasMapper.getAllByAreaName(cinemaName);
    }




    @Override
    public int delCinema(Long cinemaId, Long movieId) {
        long idByCinemaIdAndMovieId = cinemaMoviesSer.getIdByCinemaIdAndMovieId(cinemaId,movieId);
        List<Long> longs = moviePlanSer.selectCMId(idByCinemaIdAndMovieId);
        hallSeatMapper.deleteByMoviePlanIds(longs);
        moviePlanSer.deleteByCinemaMovieId(idByCinemaIdAndMovieId);
        cinemaMoviesSer.deleteByCinemaIdAndMovieId(cinemaId,movieId);
        return 1;
    }
}
