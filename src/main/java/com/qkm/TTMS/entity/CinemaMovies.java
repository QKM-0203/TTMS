package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName(value ="cinema_movies")
@Data
@NoArgsConstructor
public class CinemaMovies implements Serializable {


    public CinemaMovies(Movie movie, Float cinemaMovieMoney) {
        this.movie = movie;
        this.cinemaMovieMoney = cinemaMovieMoney;
    }

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 电影院的id
     */
    @TableField(value = "cinema_id")
    private Integer cinemaId;

    /**
     * 电影id
     */
    @TableField(value = "movie_id")
    private Integer movieId;

    /**
     * 电影
     */
    @TableField(value = "movie_id")
    private Movie movie;

    /**
     * 电影的最低价格
     */
    @TableField(value = "movie_lowMoney")
    private Float movieLowMoney;


    /**
     * 电影一天赚的钱
     */
    @TableField(value = "day_money")
    private Float dayMoney;



    /**
     * 电影总共赚的钱
     */
    @TableField(value = "cinema_movie_money")
    private Float cinemaMovieMoney;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}