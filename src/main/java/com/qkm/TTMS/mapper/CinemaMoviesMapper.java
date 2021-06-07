package com.qkm.TTMS.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qkm.TTMS.entity.CinemaMovies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkm.TTMS.entity.Movie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CinemaMoviesMapper extends BaseMapper<CinemaMovies> {
   int  getIdByCinemaIdAndMovieId(@Param("cinemaId")int cinemaId,@Param("movieId")int movieId);
   IPage<Movie> getListMovieIdByCinemaId(@Param("page") IPage<Movie> page,@Param("cinemaId")int cinemaId);
   int deleteByCinemaIdAndMovieId(@Param("cinemaId") int cinemaId, @Param("movieId") int movieId);
   int  deleteByMovieId(@Param("movieId") int movieId);
   int setMovieLowMoneyByCinemaIdAndMovieId(@Param("movieLowMoney") Double movieLowMoney, @Param("cinemaId") int cinemaId, @Param("movieId") int movieId);
   CinemaMovies getAllByCinemaId(@Param("cinemaId") int cinemaId);
   int deleteByCinemaId(@Param("cinemaId") int cinemaId);
   List<Integer> getListIdByCinemaId(@Param("cinemaId")int cinemaId);
   List<Movie> getListMovieIdByCinemaIdNotPage(@Param("cinemaId")int cinemaId);
   int addMoney(@Param("money")Double money,@Param("cinemaId") int id);
   int downMoney(@Param("money")Double money,@Param("cinemaId") int id);


}




