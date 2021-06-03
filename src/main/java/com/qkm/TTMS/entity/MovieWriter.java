package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="movie_writer")
@Data
public class MovieWriter implements Serializable {
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
     * 编剧的照片
     */
    @TableField(value = "screenwriter_picture")
    private String screenwriterPicture;

    /**
     * 编剧的名字
     */
    @TableField(value = "screenwriter_name")
    private String screenwriterName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}