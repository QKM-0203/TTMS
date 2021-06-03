package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName city
 */
@TableName(value ="city")
@Data
public class City implements Serializable {
    /**
     * 城市的id
     */
    @TableField(value = "id")
    private Integer id;

    /**
     * 城市的名字
     */
    @TableField(value = "city_name")
    private String cityName;

    /**
     * 省的Id
     */
    @TableField(value = "province_id")
    private Integer provinceId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}