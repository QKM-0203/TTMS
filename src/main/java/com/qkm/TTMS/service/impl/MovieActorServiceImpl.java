package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieActor;
import com.qkm.TTMS.mapper.MovieActorMapper;
import com.qkm.TTMS.service.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieActorServiceImpl implements MovieActorService {

    private final MovieActorMapper mapper;

    public MovieActorServiceImpl(MovieActorMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateActorPicture(String actorPicture,int id) {
        return mapper.updateActorPictureById(actorPicture,id);

    }

    @Override
    public int updateActorById(MovieActor movieActor) {
        return mapper.updateById(movieActor);
    }
}
