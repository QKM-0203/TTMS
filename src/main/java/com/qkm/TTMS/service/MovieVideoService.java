package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieVideo;

public interface MovieVideoService {
    int updateVideo(String video,int id);
    int updateVideoById(MovieVideo movieVideo);
}
