package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movie_video
 */
@TableName(value ="movie_video")
@Data
public class MovieVideo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Long movieId;

    /**
     * 电影的视频
     */
    @TableField(value = "movie_video")
    private String movieVideo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}