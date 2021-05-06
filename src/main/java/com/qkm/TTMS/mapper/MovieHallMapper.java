package com.qkm.TTMS.mapper;
import org.apache.ibatis.annotations.Param;

import com.qkm.TTMS.entity.MovieHall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.qkm.TTMS.entity.MovieHall
 */
public interface MovieHallMapper extends BaseMapper<MovieHall> {
    int deleteByCinemaId(@Param("cinemaId") Long cinemaId);
}




