package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="movie_hall")
@Data
public class MovieHall implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电影院的id
     */
    @TableField(value = "cinema_id")
    private Integer cinemaId;

    /**
     * 座位行数
     */
    @TableField(value = "seat_line")
    private Integer seatLine;

    /**
     * 座位列数
     */
    @TableField(value = "seat_column")
    private Integer seatColumn;

    /**
     * 演出厅的名字
     */
    @TableField(value = "hall_name")
    private String hallName;

    /**
     * 影厅的类型
     */
    @TableField(value = "movie_type")
    private String movieType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}