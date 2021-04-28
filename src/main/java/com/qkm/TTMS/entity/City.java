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
 * @TableName city
 */
@TableName(value ="city")
@Data
public class City implements Serializable {
    /**
     * 市的id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 省的id
     */
    @TableField(value = "province_id")
    private Integer provinceId;

    /**
     * 市的名字
     */
    @TableField(value = "city_name")
    private String cityName;


    /**
     * 区
     */
    private List<Area> areaName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}