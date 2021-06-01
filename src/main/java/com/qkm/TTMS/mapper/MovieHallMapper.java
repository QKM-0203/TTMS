package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MovieHall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieHallMapper extends BaseMapper<MovieHall> {
    int deleteByCinemaId(@Param("cinemaId") int cinemaId);
}




