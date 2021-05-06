package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MoviePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.MoviePlan
 */
@Repository
public interface MoviePlanMapper extends BaseMapper<MoviePlan> {
    List<MoviePlan> getMoviePlan(@Param("movieId")Long movieId, @Param("cinemaId")Long cinemaId);
    int deleteByCinemaMovieId(@Param("cinemaMovieId") Long cinemaMovieId);
    List<Long> selectListCMId(@Param("list") List<Long> list);
    List<Long> selectCMId(@Param("cinemaMovieId") Long cinemaMovieId);
    int deleteByCinemaMovieIds(@Param("cinemaMovieId")List<Long> cinemaMovieId);

}




