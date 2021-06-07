package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieActor;

public interface MovieActorService {
    int updateActorPicture(String actorPicture,int id);
    int updateActorById(MovieActor movieActor);
}
