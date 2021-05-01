package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.Movie;

import java.util.List;

public interface MovieSer {
    List<Movie> getMoviesOn();
    List<Movie> getMoviesSoon();
    List<Movie> getMoviesHot();
}
