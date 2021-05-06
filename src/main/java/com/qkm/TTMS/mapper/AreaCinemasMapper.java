package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.AreaCinemas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkm.TTMS.entity.CinemaMovies;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.AreaCinemas
 */
@Repository
public interface AreaCinemasMapper extends BaseMapper<AreaCinemas> {
    List<AreaCinemas> getCinemaMoviesByCinemaId(@Param("areaName") String areaName, @Param("movieId") Long movieId);
    AreaCinemas getAllById(@Param("id") Long id);
    List<Long> getListID();
    List<AreaCinemas> getAll();
    int deleteById(@Param("cinemaId") Long cinemaId);
    int addMoney(@Param("money")Long money,@Param("cinemaId") Long cinemaId);
    int downMoney(@Param("money")Long money,@Param("cinemaId") Long cinemaId);
    List<AreaCinemas> getAllByAreaName(@Param("cinemaName") String cinemaName);
}




