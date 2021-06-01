package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieVideoMapper extends BaseMapper<MovieVideo> {
    int deleteByMovieId(@Param("movieId") int movieId);
}




