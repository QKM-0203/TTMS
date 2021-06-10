package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="movie_user")
@Data
@NoArgsConstructor
public class MovieUser implements Serializable{
    public MovieUser(String accounts) {
        this.accounts = accounts;
    }

    public MovieUser( String accounts,Date createTime, Float sellMoney) {
        this.accounts = accounts;
        this.createTime = createTime;
        this.sellMoney = sellMoney;

    }

    /**
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名`
     */
    @TableField(value = "accounts")
    private String accounts;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 注册时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 头像
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 所在城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 职业
     */
    @TableField(value = "job")
    private String job;

    /**
     * 个性签名
     */
    @TableField(value = "personalized_signature")
    private String personalizedSignature;

    /**
     *电影院id
     */
    @TableField(value = "cinema_id")
    private Integer cinemaId;

    /**
     *售货员id
     */
    @TableField(value = "sell_id")
    private Integer sellId;

    /**
     *售货员赚得钱
     */
    @TableField(value = "sell_id")
    private Float sellMoney;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public MovieUser(Integer id, String accounts, String password, String nickname, Date createTime, String icon, String gender, Date birthday, String city, String job, String personalizedSignature, Integer cinemaId, Integer sellId) {
        this.id = id;
        this.accounts = accounts;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
        this.icon = icon;
        this.gender = gender;
        this.birthday = birthday;
        this.city = city;
        this.job = job;
        this.personalizedSignature = personalizedSignature;
        this.cinemaId = cinemaId;
        this.sellId = sellId;
    }

    private List<Roles> roles;

}