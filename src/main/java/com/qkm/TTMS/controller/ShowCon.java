package com.qkm.TTMS.controller;

import com.alibaba.fastjson.JSON;
import com.qkm.TTMS.entity.Movie;
import com.qkm.TTMS.service.impl.MovieSerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowCon {

    private final MovieSerImpl movieSerImpl;

    public ShowCon(MovieSerImpl movieSerImpl) {
        this.movieSerImpl = movieSerImpl;
    }

    @GetMapping("/getMovies")
    public String getMovies(){
        return  JSON.toJSONString(movieSerImpl.getMovies());
    }
}
