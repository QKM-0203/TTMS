package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.MovieProducer;
import com.qkm.TTMS.mapper.MovieProducerMapper;
import com.qkm.TTMS.service.MovieProducerService;
import org.springframework.stereotype.Repository;

@Repository
public class MovieProducerServiceImpl implements MovieProducerService {

    private final MovieProducerMapper mapper;

    public MovieProducerServiceImpl(MovieProducerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateProducerPicture(String producerPicture, int id) {
        return mapper.updateProducerPictureById(producerPicture,id);
    }

    @Override
    public int updateProducerById(MovieProducer movieProducer) {
        return mapper.updateById(movieProducer);
    }
}
