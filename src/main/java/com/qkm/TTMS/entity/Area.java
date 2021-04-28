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
 * @TableName area
 */
@TableName(value ="area")
@Data
public class Area implements Serializable {
    /**
     * 区的名字
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 市id
     */
    @TableField(value = "city_id")
    private Integer cityId;

    /**
     * 区的名字
     */
    @TableField(value = "area_name")
    private String areaName;

    /**
     * 区里的所有电影院
     */
    private List<AreaCinemas> cinemaList;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}