package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieWriter;
import org.springframework.stereotype.Repository;

public interface MovieWriteService {
    int updateWriterPicture(String writePicture,int id);
    int updateWriterById(MovieWriter movieWriter);
}
