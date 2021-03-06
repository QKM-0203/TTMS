package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;


@TableName(value ="movie_picture")
@Data
public class MoviePicture implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "movie_id")
    private Integer movieId;

    /**
     * 
     */
    @TableField(value = "movie_picture")
    private String moviePicture;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}