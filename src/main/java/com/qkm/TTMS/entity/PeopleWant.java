package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="people_want")
@Data
public class PeopleWant implements Serializable {
    /**
     * 主键id
     */
    @TableField(value = "id")
    private Integer id;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Integer movieId;

    /**
     * user id
     */
    @TableField(value = "accounts")
    private String accounts;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}