package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.MovieMapper;
import com.qkm.TTMS.service.MovieSer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieSerImpl implements MovieSer {
    private final MovieMapper movieMapper;

    public MovieSerImpl(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    @Override
    public List<Movie> getMoviesOn() {
        return movieMapper.getMoviesOn();
    }

    @Override
    public List<Movie> getMoviesSoon() {
        return movieMapper.getMoviesSoon();
    }

    @Override
    public List<Movie> getMoviesHot() {
        return movieMapper.getMoviesHot();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieMapper.getMovieByMovieId(id);
    }
}
