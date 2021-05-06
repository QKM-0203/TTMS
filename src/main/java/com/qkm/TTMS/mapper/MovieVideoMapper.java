package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieVideo
 */
@Repository
public interface MovieVideoMapper extends BaseMapper<MovieVideo> {
    int deleteByMovieId(@Param("movieId") Long movieId);
}




