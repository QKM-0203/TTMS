package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.mapper.MovieMapper;
import com.qkm.TTMS.service.MovieSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieSerImpl implements MovieSer {
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> getMovies() {
        return movieMapper.getMovies();
    }
}
