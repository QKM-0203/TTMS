package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;


@TableName(value ="area_cinemas")
@Data
public class AreaCinemas implements Serializable {

    public AreaCinemas(Integer id) {
        this.id = id;
    }

    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 区的名字
     */
    @TableField(value = "area_id")
    private String areaId;

    /**
     * 电影院的名称
     */
    @TableField(value = "cinema_name")
    private String cinemaName;

    /**
     * 电影院的照片
     */
    @TableField(value = "cinema_picture")
    private String cinemaPicture;

    /**
     * 影院的地址
     */
    @TableField(value = "cinema_address")
    private String cinemaAddress;

    /**
     * 电影院赚的钱
     */
    @TableField(value = "cinema_money")
    private Double cinemaMoney;

    /**
     * 电影院的电话号码
     */
    @TableField(value = "cinema_number")
    private String cinemaNumber;

    /**
     * 电影院某部电影的最低价格
     */
    private Float lawMoney;

    /**
     * 电影院里面的某部电影Id
     */
    private Integer movieId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}