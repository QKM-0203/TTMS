package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieActor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieActorMapper extends BaseMapper<MovieActor> {
    int deleteByMovieId(@Param("movieId") int movieId);
    int updateActorPictureById(@Param("actorPicture") String actorPicture, @Param("id") Integer id);
}




