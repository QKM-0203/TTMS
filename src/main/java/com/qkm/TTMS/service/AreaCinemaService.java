package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.AreaCinemas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AreaCinemaService {
    List<AreaCinemas> getCinemaMoviesByCinemaId( String areaName, Long movieId);
    AreaCinemas getAllById(Long id);
    List<Long> getListID();
    List<AreaCinemas> getAll();
    int deleteById(Long cinemaId);
    int addMoney(Long money, Long cinemaId);
    int downMoney(Long money, Long cinemaId);
    List<AreaCinemas> getAllByAreaName( String cinemaName);

}
