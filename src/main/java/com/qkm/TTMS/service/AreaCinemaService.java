package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.AreaCinemas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AreaCinemaService {
    List<AreaCinemas> getCinemaByMovieId(int page, int movieId);
    AreaCinemas getAllById(int id);
    List<AreaCinemas> getAllCinemas(int page);
    int deleteById(int cinemaId);
    int addMoney(Double money, int cinemaId);
    int downMoney(Double money, int cinemaId);
    List<AreaCinemas> getCinemasByCinemaName( String cinemaName,int page);
    Map<String,Object> getCinemasByProvinceAndCityAndArea(int areaId, int movieId, int page);
    Map<String,Object> getCinemasByProvince(int provinceId, int movieId, int page);
    Map<String,Object> getCinemasByProvinceAndCity(int cityId,int movieId, int page);
    Map<String,Object> getAllCinemasByProvinceAndCityAndArea(int areaId, int page);
    Map<String,Object> getAllCinemasByProvince(int provinceId,  int page);
    Map<String,Object> getAllCinemasByProvinceAndCity(int cityId,int page);



}
