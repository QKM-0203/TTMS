package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName area_cinemas
 */
@TableName(value ="area_cinemas")
@Data
public class AreaCinemas implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区的id
     */
    @TableField(value = "area_id")
    private Long areaId;

    /**
     * 电影院的名称
     */
    @TableField(value = "cinema_name")
    private String cinemaName;

    /**
     * 
     */
    @TableField(value = "cinema_picture")
    private String cinemaPicture;

    /**
     * 影院的地址
     */
    @TableField(value = "movie_address")
    private String movieAddress;

    /**
     * 该影院的所有电影
     */
    private List<Movie> movieList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}