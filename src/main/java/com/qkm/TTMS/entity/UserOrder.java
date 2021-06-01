package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;


@TableName(value ="user_order")
@Data
public class UserOrder implements Serializable {
    /**
     * 主键也是订单号
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 使用者的id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 电影院的名字
     */
    @TableField(value = "cinema_name")
    private String cinemaName;

    /**
     * 电影的名字
     */
    @TableField(value = "movie_name")
    private String movieName;

    /**
     * 订单的总钱数
     */
    @TableField(value = "order_money")
    private Double orderMoney;

    /**
     * 电影院的id
     */
    @TableField(value = "cinema_id")
    private Integer cinemaId;

    /**
     * 一张票的钱
     */
    @TableField(value = "ticket_money")
    private Double ticketMoney;

    /**
     * 电影的开始时间
     */
    @TableField(value = "movie_start_time")
    private Date movieStartTime;

    /**
     * 订单状态/未支付or已支付or退款
     */
    @TableField(value = "order_status")
    private String orderStatus;

    /**
     * 演出厅的名字
     */
    @TableField(value = "hall_name")
    private String hallName;

    /**
     * 订单的座位
     */
    private List<HallSeat> hallSeatList;


    /**
     * 电影的类型
     */
    @TableField(value = "movie_type")
    private String movieType;

    /**
     * 电影的时长
     */
    @TableField(value = "movie_time")
    private Integer movieTime;


    /**
     * 电影的id
     */
    @TableId(value = "movie_id")
    private Integer movieId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}