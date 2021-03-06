package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@TableName(value ="movie_video")
@Data
public class MovieVideo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Integer movieId;

    /**
     * 电影的视频
     */
    @TableField(value = "movie_video")
    private String movieVideo;

    /**
     * 视频的标题
     */
    @TableField(value = "video_title")
    private String videoTitle;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}