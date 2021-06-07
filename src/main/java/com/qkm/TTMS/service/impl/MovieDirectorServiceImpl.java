package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieDirector;
import com.qkm.TTMS.mapper.MovieDirectorMapper;
import com.qkm.TTMS.service.MovieDirectorService;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDirectorServiceImpl implements MovieDirectorService {

    private MovieDirectorMapper mapper;
    @Override
    public int updateDirectorPicture(String actorPicture, int id) {
        return mapper.updateDirectorPictureById(actorPicture,id);
    }

    @Override
    public int updateDirectorById(MovieDirector movieDirector) {
        return mapper.updateById(movieDirector);
    }
}
