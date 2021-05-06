package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieHall;

import java.util.List;

public interface HallService {
    int delHall(Long id);
    List<MovieHall> getHalls(Long cinemaId);
    int  updateHall(MovieHall movieHall);
    Long  addHall( MovieHall movieHall);
    int deleteByCinemaId( Long cinemaId);

}
