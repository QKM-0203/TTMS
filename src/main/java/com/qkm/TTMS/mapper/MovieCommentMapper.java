package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieComment
 */
@Repository
public interface MovieCommentMapper extends BaseMapper<MovieComment> {
    int deleteByMovieId(@Param("movieId") Long movieId);
}




