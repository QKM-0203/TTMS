package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieVideo;
import com.qkm.TTMS.mapper.MovieVideoMapper;
import com.qkm.TTMS.service.MovieVideoService;
import org.springframework.stereotype.Repository;

@Repository
public class MovieVideoImpl implements MovieVideoService {

    private final MovieVideoMapper mapper;

    public MovieVideoImpl(MovieVideoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateVideo(String video, int id) {
        return mapper.updateVideoById(video,id);
    }

    @Override
    public int updateVideoById(MovieVideo movieVideo) {
        return mapper.updateById(movieVideo);
    }
}
