package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AreaCinemaServiceImpl implements AreaCinemaService {



    private final AreaCinemasMapper areaCinemasMapper;

    public AreaCinemaServiceImpl(AreaCinemasMapper areaCinemasMapper) {
        this.areaCinemasMapper = areaCinemasMapper;

    }
    @Override
    public List<AreaCinemas> getCinemaMoviesByCinemaId(String areaName, int movieId) {
        return areaCinemasMapper.getCinemaMoviesByCinemaId(areaName,movieId);
    }

    @Override
    public AreaCinemas getAllById(int id) {
        return areaCinemasMapper.getAllById(id);
    }

    @Override
    public List<Integer> getListID() {
        return areaCinemasMapper.getListID();
    }

    @Override
    public List<AreaCinemas> getAll() {
        return areaCinemasMapper.getAll();
    }

    @Override
    public int deleteById(int cinemaId) {
        return areaCinemasMapper.deleteById(cinemaId);
    }

    @Override
    public int addMoney(Double money, int cinemaId) {
        return areaCinemasMapper.addMoney(money, cinemaId);
    }

    @Override
    public int downMoney(Double money, int cinemaId) {
        return areaCinemasMapper.downMoney(money, cinemaId);
    }

    @Override
    public List<AreaCinemas> getAllByAreaName(String cinemaName) {
        return areaCinemasMapper.getAllByAreaName(cinemaName);
    }


}
