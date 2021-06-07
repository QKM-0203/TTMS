package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieDirector;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieDirectorMapper extends BaseMapper<MovieDirector> {
    int deleteByMovieId(@Param("movieId") int movieId);
    int updateDirectorPictureById(@Param("directorPicture") String directorPicture, @Param("id") Integer id);
}




