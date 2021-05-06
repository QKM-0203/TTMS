package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName movie_producer
 */
@TableName(value ="movie_producer")
@Data
public class MovieProducer implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 电影的id
     */
    @TableField(value = "movie_id")
    private Long movieId;

    /**
     * 出品人的名字
     */
    @TableField(value = "producer_name")
    private String producerName;

    /**
     * 出品人的照片
     */
    @TableField(value = "producer_picture")
    private String producerPicture;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}