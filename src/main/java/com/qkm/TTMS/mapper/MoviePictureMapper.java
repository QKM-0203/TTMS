package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MoviePicture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface MoviePictureMapper extends BaseMapper<MoviePicture> {
    int deleteByMovieId(@Param("movieId") int movieId);
}




