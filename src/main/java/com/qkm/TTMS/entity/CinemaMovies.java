package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName cinema_movies
 */
@TableName(value ="cinema_movies")
@Data
public class CinemaMovies implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 电影院的id
     */
    @TableField(value = "cinema_id")
    private Long cinemaId;

    /**
     * 电影id
     */
    @TableField(value = "movie_id")
    private Long movieId;

    /**
     * 电影的最低价格
     */
    @TableField(value = "movie_lowMoney")
    private Double movieLowmoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}