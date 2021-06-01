package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieHall;

import java.util.List;

public interface HallService {
    int delHall(int id);
    List<MovieHall> getHalls(int cinemaId,int page);
    int  updateHall(MovieHall movieHall);
    int  addHall( MovieHall movieHall);
    int deleteByCinemaId(int cinemaId);

}
