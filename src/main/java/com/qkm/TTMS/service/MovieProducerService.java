package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.MovieProducer;


public interface MovieProducerService {
    int updateProducerPicture(String producerPicture,int id);
    int updateProducerById(MovieProducer movieProducer);
}
