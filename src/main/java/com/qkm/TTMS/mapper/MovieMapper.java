package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieMapper extends BaseMapper<Movie> {
   List<Movie>  getMoviesOn();
   List<Movie>  getMoviesSoon();
   List<Movie>  getMoviesHot();
   int updateByName(@Param("movieName")String movieName,@Param("movieBrief")String movieBrief,@Param("movieMoney")String movieMoney
   ,@Param("wantLook") String WantLook);
   int setHead(@Param("head")String head,@Param("id") int id);
   Movie  getMovieByMovieId(@Param("id") int id);
   int  addMoney(@Param("money")Double money,@Param("movieId") int movieId);
   int  downMoney(@Param("money")Double money,@Param("movieId") int movieId);
   List<Movie> selectMovieByListId(@Param("listId")List<Integer> listId);
}




