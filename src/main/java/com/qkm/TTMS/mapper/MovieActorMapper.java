package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieActor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieActor
 */
@Repository
public interface MovieActorMapper extends BaseMapper<MovieActor> {
    int deleteByMovieId(@Param("movieId") Long movieId);
}




