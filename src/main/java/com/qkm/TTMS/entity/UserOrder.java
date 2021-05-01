package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName user_order
 */
@TableName(value ="user_order")
@Data
public class UserOrder implements Serializable {
    /**
     * 主键也是订单号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 使用者的id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 下单的时间
     */
    @TableField(value = "order_date")
    private Date orderDate;

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
    private Integer orderMoney;

    /**
     * 电影院的id
     */
    @TableField(value = "cinema_id")
    private Long cinemaId;

    /**
     * 一张票的钱
     */
    @TableField(value = "ticket_money")
    private Integer ticketMoney;

    /**
     * 电影的开始时间
     */
    @TableField(value = "movie_time")
    private Date movieTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 选择的座位信息/已被选择的座位
     */
    private List<HallSeat> hallSeatList;
}