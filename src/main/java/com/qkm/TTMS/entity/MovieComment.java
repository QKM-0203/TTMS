package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="movie_comment")
@Data
public class MovieComment implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Integer movieId;

    /**
     * 电影的评论
     */
    @TableField(value = "people_comment")
    private String peopleComment;

    /**
     * 评论的人的昵称
     */
    @TableField(value = "people_nickname")
    private String peopleNickname;

    /**
     * 图像
     */
    @TableField(value = "people_icon")
    private String peopleIcon;

    /**
     * 对电影的个人评分
     */
    @TableField(value = "people_score")
    private Integer peopleScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}