package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName hall_seat
 */
@TableName(value ="hall_seat")
@Data
public class HallSeat implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 座位的行
     */
    @TableField(value = "seat_line")
    private Integer seatLine;

    /**
     * 座位的列
     */
    @TableField(value = "seat_column")
    private Integer seatColumn;

    /**
     * 某一个电影院的某个电影的某个时间段的id
     */
    @TableField(value = "movie_plan_id")
    private Long moviePlanId;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long orderId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}