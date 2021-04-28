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
     * 演出厅的id
     */
    @TableField(value = "hall_id")
    private Long hallId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}