package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieWriter;
import com.qkm.TTMS.mapper.MovieWriterMapper;
import com.qkm.TTMS.service.MovieWriteService;
import org.springframework.stereotype.Repository;

@Repository
public class MovieWriteServiceImpl implements MovieWriteService {

    private final MovieWriterMapper mapper;

    public MovieWriteServiceImpl(MovieWriterMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateWriterPicture(String writerPicture, int id) {
        return  mapper.updateWriterPictureById(writerPicture,id);
    }

    @Override
    public int updateWriterById(MovieWriter movieWriter) {
        return mapper.updateById(movieWriter);
    }
}
