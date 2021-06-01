package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

@TableName(value ="movie_user")
@Data
public class MovieUser implements Serializable{

    /**
     *
     */
    @TableId(value = "id")
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
    private int sellId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public MovieUser(Integer id, String accounts, String password, String nickname, Date createTime, String icon, String gender, Date birthday, String city, String job, String personalizedSignature) {
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
    }

    private List<Roles> roles;

}