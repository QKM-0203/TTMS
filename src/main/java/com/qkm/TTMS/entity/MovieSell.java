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
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "sell_id")
    private Integer sellId;

    /**
     * 售票员赚得钱
     */
    @TableField(value = "sell_money")
    private Long sellMoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}