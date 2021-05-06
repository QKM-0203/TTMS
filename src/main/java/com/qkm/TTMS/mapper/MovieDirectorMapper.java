package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieDirector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieDirector
 */
@Repository
public interface MovieDirectorMapper extends BaseMapper<MovieDirector> {
    int deleteByMovieId(@Param("movieId") Long movieId);
}




