package com.qkm.TTMS.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;


@TableName(value ="area_cinemas")
@Data
@NoArgsConstructor//JOSN反序列化需要需要无参构造器
public class AreaCinemas implements Serializable {
    public AreaCinemas(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public AreaCinemas(Integer id) {
        this.id = id;
    }


    public AreaCinemas(Integer id, Integer areaId, String cinemaName, String cinemaPicture, String cinemaAddress, Double cinemaMoney, String cinemaNumber, Float lawMoney) {
        this.id = id;
        this.areaId = areaId;
        this.cinemaName = cinemaName;
        this.cinemaPicture = cinemaPicture;
        this.cinemaAddress = cinemaAddress;
        this.cinemaMoney = cinemaMoney;
        this.cinemaNumber = cinemaNumber;
        this.lawMoney = lawMoney;
    }

    public AreaCinemas(Integer id, Integer areaId, String cinemaName, String cinemaPicture, String cinemaAddress, String cinemaEmail, Double cinemaMoney, String cinemaNumber, Float lawMoney) {
        this.id = id;
        this.areaId = areaId;
        this.cinemaName = cinemaName;
        this.cinemaPicture = cinemaPicture;
        this.cinemaAddress = cinemaAddress;
        this.cinemaEmail = cinemaEmail;
        this.cinemaMoney = cinemaMoney;
        this.cinemaNumber = cinemaNumber;
        this.lawMoney = lawMoney;
    }

    public AreaCinemas(Integer id, Integer areaId, String cinemaName, String cinemaPicture, String cinemaAddress, Double cinemaMoney, String cinemaNumber) {
        this.id = id;
        this.areaId = areaId;
        this.cinemaName = cinemaName;
        this.cinemaPicture = cinemaPicture;
        this.cinemaAddress = cinemaAddress;
        this.cinemaMoney = cinemaMoney;
        this.cinemaNumber = cinemaNumber;
    }

    public AreaCinemas(Integer id, Integer areaId, String cinemaName, String cinemaPicture, String cinemaAddress, String cinemaEmail, Double cinemaMoney, String cinemaNumber) {
        this.id = id;
        this.areaId = areaId;
        this.cinemaName = cinemaName;
        this.cinemaPicture = cinemaPicture;
        this.cinemaAddress = cinemaAddress;
        this.cinemaEmail = cinemaEmail;
        this.cinemaMoney = cinemaMoney;
        this.cinemaNumber = cinemaNumber;

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
    private Integer areaId;

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
     * 影院的邮箱
     */
    @TableField(value = "cinema_email")
    private String cinemaEmail;

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