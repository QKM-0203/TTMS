package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.Movie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.Movie
 */
@Repository
public interface MovieMapper extends BaseMapper<Movie> {
       List<Movie> getMovies();
}




