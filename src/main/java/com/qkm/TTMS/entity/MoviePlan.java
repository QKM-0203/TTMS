package com.qkm.TTMS.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName movie_plan
 */
@TableName(value ="movie_plan")
@Data
public class MoviePlan implements Serializable,Comparable<MoviePlan> {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 电影的开始时间
     */
    @TableField(value = "movie_start_time")
    private Date movieStartTime;

    /**
     * 电影的结束时间
     */
    @TableField(value = "movie_end_time")
    private Date movieEndTime;

    /**
     * 演出计划的时间


     */
    @TableField(value = "plan_date")
    private Date planDate;

    /**
     * 电影院里面对应的电影id
     */
    @TableField(value = "cinema_movie_id")
    private Long cinemaMovieId;

    /**
     * 该时间段对应的演出厅id
     */
    @TableField(value = "hall_id")
    private Long hallId;



    /**
     * 该时间段对应的演出厅信息
     */
    @TableField(value = "hall_id")
    private MovieHall hall;

    /**
     * 某个时间段的钱
     */
    @TableField(value = "ticket_money")
    private Float ticketMoney;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public int compareTo(MoviePlan o) {
        if (this.getMovieStartTime().compareTo(o.getMovieStartTime()) == 1 ) {
            return 1;
        } else if (this.getMovieStartTime().compareTo(o.getMovieStartTime()) == -1) {
            return -1;
        } else {
            //如果当天的开始时间相同就按计划id进行排序
            return this.getId().compareTo(o.getId());
        }
    }
}