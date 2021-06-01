package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="cinema_movies")
@Data
public class CinemaMovies implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
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
     * 电影的最低价格
     */
    @TableField(value = "movie_lowMoney")
    private Double movieLowMoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}