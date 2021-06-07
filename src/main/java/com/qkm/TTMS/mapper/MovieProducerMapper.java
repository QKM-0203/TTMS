package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieProducer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieProducerMapper extends BaseMapper<MovieProducer> {
    int deleteByMovieId(@Param("movieId") int movieId);
    int updateProducerPictureById(@Param("producerPicture") String producerPicture, @Param("id") Integer id);

}




