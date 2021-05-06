package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movie_actor
 */
@TableName(value ="movie_actor")
@Data
public class MovieActor implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 电影的人物图片
     */
    @TableField(value = "actor_picture")
    private String actorPicture;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Long movieId;

    /**
     * 真名
     */
    @TableField(value = "actor_name")
    private String actorName;

    /**
     * 角色的名字
     */
    @TableField(value = "role_name")
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}