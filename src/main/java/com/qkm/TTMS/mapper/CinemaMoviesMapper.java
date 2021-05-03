package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.CinemaMovies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.CinemaMovies
 */
@Repository
public interface CinemaMoviesMapper extends BaseMapper<CinemaMovies> {
   long  getIdByCinemaIdAndMovieId(@Param("cinemaId")long cinemaId,@Param("movieId")long movieId);
   List<Long> getListMovieIdByCinemaId(@Param("cinemaId")long cinemaId);
   int deleteByCinemaIdAndMovieId(@Param("cinemaId") Long cinemaId, @Param("movieId") Long movieId);
   int  deleteByMovieId(@Param("movieId") Long movieId);
   int setMovieLowMoneyByCinemaIdAndMovieId(@Param("movieLowMoney") Double movieLowMoney, @Param("cinemaId") Long cinemaId, @Param("movieId") Long movieId);
   CinemaMovies getAllByCinemaId(@Param("cinemaId") Long cinemaId);
}




