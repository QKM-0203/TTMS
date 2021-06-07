package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieDirector;

public interface MovieDirectorService {
    int updateDirectorPicture(String directorPicture,int id);
    int updateDirectorById(MovieDirector movieDirector);
}
