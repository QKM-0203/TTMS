package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieWriter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieWriterMapper extends BaseMapper<MovieWriter> {
    int deleteByMovieId(@Param("movieId") int movieId);
    int updateWriterPictureById(@Param("writerPicture") String writerPicture, @Param("id") Integer id);
}




