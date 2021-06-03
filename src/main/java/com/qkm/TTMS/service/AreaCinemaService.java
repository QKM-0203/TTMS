package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.AreaCinemas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AreaCinemaService {
    List<AreaCinemas> getCinemaByCinemaId(int page, int movieId);
    AreaCinemas getAllById(int id);
    List<AreaCinemas> getAll(int page);
    int deleteById(int cinemaId);
    int addMoney(Double money, int cinemaId);
    int downMoney(Double money, int cinemaId);
    List<AreaCinemas> getAllByAreaName( String cinemaName,int page);
    Map<String,Object> getCinemasByProvinceAndCityAndArea(int areaId, int movieId, int page);
    Map<String,Object> getCinemasByProvince(int provinceId, int movieId, int page);
    Map<String,Object> getCinemasByProvinceAndCity(int cityId,int movieId, int page);



}
