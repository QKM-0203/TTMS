package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.AreaCinemas;
import com.qkm.TTMS.mapper.AreaCinemasMapper;
import com.qkm.TTMS.mapper.MoviePlanMapper;
import com.qkm.TTMS.service.AreaCinemaSer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AreaCinemaSerImpl implements AreaCinemaSer {

    private final AreaCinemasMapper areaCinemasMapper;

    public  AreaCinemaSerImpl(AreaCinemasMapper areaCinemasMapper) {
        this.areaCinemasMapper = areaCinemasMapper;

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
    public int updateMoney(Long money, Long cinemaId) {

            return areaCinemasMapper.updateMoney(money, cinemaId);


    }

    @Override
    public List<AreaCinemas> getAllByAreaName(String cinemaName) {
        return areaCinemasMapper.getAllByAreaName(cinemaName);
    }
}
