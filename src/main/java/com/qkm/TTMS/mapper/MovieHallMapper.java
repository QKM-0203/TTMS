package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MovieHall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.qkm.TTMS.entity.MovieHall
 */
public interface MovieHallMapper extends BaseMapper<MovieHall> {
    int deleteByCinemaId(@Param("cinemaId") Long cinemaId);
}




