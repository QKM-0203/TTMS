package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.AreaCinemas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AreaCinemaService {
    List<AreaCinemas> getCinemaMoviesByCinemaId( String areaName, int movieId);
    AreaCinemas getAllById(int id);
    List<Integer> getListID();
    List<AreaCinemas> getAll();
    int deleteById(int cinemaId);
    int addMoney(Double money, int cinemaId);
    int downMoney(Double money, int cinemaId);
    List<AreaCinemas> getAllByAreaName( String cinemaName);

}
