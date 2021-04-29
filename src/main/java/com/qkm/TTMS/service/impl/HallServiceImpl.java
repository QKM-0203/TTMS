package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.mapper.MovieHallMapper;
import com.qkm.TTMS.service.HallService;
import org.springframework.stereotype.Repository;

@Repository
public class HallServiceImpl implements HallService {

    private final MovieHallMapper movieHallMapper;

    public HallServiceImpl(MovieHallMapper movieHallMapper) {
        this.movieHallMapper = movieHallMapper;
    }

    public void delHall(Integer id){
         movieHallMapper.deleteById(id);
    }
}
