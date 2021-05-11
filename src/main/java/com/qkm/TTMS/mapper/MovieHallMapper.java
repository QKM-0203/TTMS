package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MovieHall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieHall
 */
@Repository
public interface MovieHallMapper extends BaseMapper<MovieHall> {
    int deleteByCinemaId(@Param("cinemaId") Long cinemaId);
}




