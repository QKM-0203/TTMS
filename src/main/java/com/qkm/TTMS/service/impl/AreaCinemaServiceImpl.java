package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.Area;
import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.entity.City;
import com.qkm.TTMS.mapper.*;
import com.qkm.TTMS.service.AreaCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AreaCinemaServiceImpl implements AreaCinemaService {

    private final CityMapper cityMapper;
    private final AreaMapper areaMapper;
    private final AreaCinemasMapper areaCinemasMapper;

    public AreaCinemaServiceImpl(AreaCinemasMapper areaCinemasMapper, CityMapper cityMapper, AreaMapper areaMapper) {
        this.areaCinemasMapper = areaCinemasMapper;
        this.cityMapper = cityMapper;
        this.areaMapper = areaMapper;
    }
    @Override
    public List<AreaCinemas> getCinemaByMovieId(int page, int movieId) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemaByCinemaId = areaCinemasMapper.getCinemaByMovieId(areaCinemasPage, movieId);
        List<AreaCinemas> records = cinemaByCinemaId.getRecords();
        if(records.size() == 0){
            return null;
        }
        records.add(new AreaCinemas(String.valueOf(cinemaByCinemaId.getPages())));
        return records;

    }

    @Override
    public AreaCinemas getAllById(int id) {
        return areaCinemasMapper.getAllById(id);
    }



    @Override
    public List<AreaCinemas> getAllCinemas(int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,20,true);
        IPage<AreaCinemas> all = areaCinemasMapper.getAllCinemas(areaCinemasPage);
        List<AreaCinemas> records = all.getRecords();
        if(records.size() == 0){
            return null;
        }
        records.add(new AreaCinemas(String.valueOf(all.getPages())));
        return records;
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
    public List<AreaCinemas> getCinemasByCinemaName(String cinemaName,int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<AreaCinemas>(page,10);
        IPage<AreaCinemas> allByAreaName = areaCinemasMapper.getAllByCinemaName(areaCinemasPage, cinemaName);
        List<AreaCinemas> records = allByAreaName.getRecords();
        if(records.size() == 0){
            return  null;
        }
        records.add(new AreaCinemas(String.valueOf(allByAreaName.getPages())));
        return records;
    }

    @Override
    public Map<String,Object> getCinemasByProvinceAndCityAndArea(int areaId, int movieId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvinceAndCityAndArea = areaCinemasMapper.getCinemasByProvinceAndCityAndArea(areaCinemasPage, areaId, movieId);
        List<AreaCinemas> records = cinemasByProvinceAndCityAndArea.getRecords();
        if(records.size() == 0){
            return  null;
        }
        records.add(new AreaCinemas(String.valueOf(cinemasByProvinceAndCityAndArea.getPages())));
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        stringObjectHashMap.put("local",null);
        return stringObjectHashMap;
    }

    @Override
    public Map<String,Object> getCinemasByProvince(int provinceId, int movieId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvince = areaCinemasMapper.getCinemasByProvince(areaCinemasPage, provinceId, movieId);
        List<AreaCinemas> records = cinemasByProvince.getRecords();
        if(records.size() != 0){
            records.add(new AreaCinemas(String.valueOf(cinemasByProvince.getPages())));
        }else{
            records.clear();
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        List<City> allByProvinceId = cityMapper.getCityByProvinceId(provinceId);
        stringObjectHashMap.put("local",allByProvinceId);
        return stringObjectHashMap;
    }

    @Override
    public Map<String,Object> getCinemasByProvinceAndCity( int cityId, int movieId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvinceAndCity = areaCinemasMapper.getCinemasByProvinceAndCity(areaCinemasPage, cityId, movieId);
        List<AreaCinemas> records = cinemasByProvinceAndCity.getRecords();
        if(records.size() != 0){
            records.add(new AreaCinemas(String.valueOf(cinemasByProvinceAndCity.getPages())));
        }else{
            records.clear();
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        List<Area> allByCityId = areaMapper.getAreaByCityId(cityId);
        stringObjectHashMap.put("local",allByCityId);
        return stringObjectHashMap;
    }

    @Override
    public Map<String, Object> getAllCinemasByProvinceAndCityAndArea(int areaId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvinceAndCityAndArea = areaCinemasMapper.getAllCinemasByProvinceAndCityAndArea(areaCinemasPage, areaId);
        List<AreaCinemas> records = cinemasByProvinceAndCityAndArea.getRecords();
        if(records.size() == 0){
            return  null;
        }
        records.add(new AreaCinemas(String.valueOf(cinemasByProvinceAndCityAndArea.getPages())));
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        stringObjectHashMap.put("local",null);
        return stringObjectHashMap;
    }

    @Override
    public Map<String, Object> getAllCinemasByProvince(int provinceId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvince = areaCinemasMapper.getAllCinemasByProvince(areaCinemasPage, provinceId);
        List<AreaCinemas> records = cinemasByProvince.getRecords();
        System.out.println(records);
        if(records.size() != 0 ){
            records.add(new AreaCinemas(String.valueOf(cinemasByProvince.getPages())));
        }else{
            records.clear();
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        List<City> allByProvinceId = cityMapper.getCityByProvinceId(provinceId);
        stringObjectHashMap.put("local",allByProvinceId);
        return stringObjectHashMap;
    }

    @Override
    public Map<String, Object> getAllCinemasByProvinceAndCity(int cityId, int page) {
        Page<AreaCinemas> areaCinemasPage = new Page<>(page,12,true);
        IPage<AreaCinemas> cinemasByProvinceAndCity = areaCinemasMapper.getAllCinemasByProvinceAndCity(areaCinemasPage, cityId);
        List<AreaCinemas> records = cinemasByProvinceAndCity.getRecords();
        if(records.size() != 0 ){
            records.add(new AreaCinemas(String.valueOf(cinemasByProvinceAndCity.getPages())));
        }else{
            records.clear();
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("data",records);
        List<Area> allByCityId = areaMapper.getAreaByCityId(cityId);
        stringObjectHashMap.put("local",allByCityId);
        return stringObjectHashMap;
    }


}
