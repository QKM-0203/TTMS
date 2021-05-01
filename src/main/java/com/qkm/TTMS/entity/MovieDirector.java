package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movie_director
 */
@TableName(value ="movie_director")
@Data
public class MovieDirector implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 导演的名字
     */
    @TableField(value = "director_name")
    private String directorName;

    /**
     * 导演的照片
     */
    @TableField(value = "director_picture")
    private String directorPicture;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Long movieId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}