package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieActor;
import com.qkm.TTMS.entity.MovieProducer;
import com.qkm.TTMS.mapper.MovieProducerMapper;
import com.qkm.TTMS.service.MovieProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieProducerServiceImpl implements MovieProducerService {

    @Autowired
    private MovieProducerMapper mapper;

    @Override
    public int updateProducerPicture(String producerPicture, int id) {
        return mapper.updateProducerPictureById(producerPicture,id);
    }

    @Override
    public int updateProducerById(MovieProducer movieProducer) {
        return mapper.updateById(movieProducer);
    }
}
