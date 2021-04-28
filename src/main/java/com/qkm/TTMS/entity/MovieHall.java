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
 * @TableName movie_hall
 */
@TableName(value ="movie_hall")
@Data
public class MovieHall implements Serializable {
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
     * 演出厅是否在使用,0未使用,1在使用
     */
    @TableField(value = "hall_status")
    private Integer hallStatus;


    /**
     * 演出厅的座位列表
     */
    private List<HallSeat> sold;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}