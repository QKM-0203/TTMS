package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movie_sell
 */
@TableName(value ="movie_sell")
@Data
public class MovieSell implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 售票员的id
     */
    @TableField(value = "sell_id")
    private Integer sellId;


    /**
     * 售票员赚得钱
     */
    @TableField(value = "sell_money")
    private Float sellMoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}