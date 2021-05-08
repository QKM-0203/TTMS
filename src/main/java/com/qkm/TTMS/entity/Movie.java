package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName movie
 */
@TableName(value ="movie")
@Data
public class Movie implements Serializable{
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 电影名字
     */
    @TableField(value = "movie_name")
    private String movieName;

    /**
     * 电影状态(正在热映/即将上映/热播电影)
     */
    @TableField(value = "movie_status")
    private Integer movieStatus;

    /**
     * 上映时间
     */
    @TableField(value = "movie_start")
    private Date movieStart;

    /**
     * 电影的时长
     */
    @TableField(value = "movie_minute")
    private Integer movieMinute;

    /**
     * 电影的简介
     */
    @TableField(value = "movie_brief")
    private String movieBrief;

    /**
     * 电影的评分
     */
    @TableField(value = "movie_score")
    private Double movieScore;

    /**
     * 电影的票房
     */
    @TableField(value = "movie_money")
    private Long movieMoney;

    /**
     * 电影的当天的票房
     */
    @TableField(value = "day_money")
    private Long dayMoney;


    /**
     * 电影的想看数目
     */
    @TableField(value = "want_look")
    private Long  wantLook;

    /**
     * 电影的类型
     */
    @TableField(value = "movie_type")
    private String movieType;

    /**
     * 电影的上映地区
     */
    @TableField(value = "movie_area")
    private String  movieArea;

    /**
     * 电影的海报
     */
    @TableField(value = "movie_head")
    private String  movieHead;


    /**
     * 电影的所有评论
     */
    private List<MovieComment> commentList;


    /**
     * 电影的所有编剧
     */
    private List<MovieWriter> writerList;

    /**
     * 电影的所有导演
     */
    private List<MovieDirector> directorList;

    /**
     * 电影的所有制片人
     */
    private List<MovieProducer> producerList;

    /**
     * 电影的所有演员
     */

     private List<MovieActor> movieActorList;

    /**
     * 电影的所有视频
     */
     private  List<MovieVideo> movieVideoList;


    /**
     * 电影的所有图集
     */
    private  List<MoviePicture> moviePictureList;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;






}