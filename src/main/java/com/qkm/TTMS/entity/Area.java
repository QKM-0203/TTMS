package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName area
 */
@TableName(value ="area")
@Data
public class Area implements Serializable {
    /**
     * 区的id
     */
    @TableField(value = "id")
    private Integer id;

    /**
     * 区的名字
     */
    @TableField(value = "area_name")
    private String areaName;

    /**
     * 市的id
     */
    @TableField(value = "city_id")
    private Integer cityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}