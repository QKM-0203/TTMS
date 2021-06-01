package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.HallSeat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HallSeatMapper extends BaseMapper<HallSeat> {
    List<HallSeat> getAllByMoviePlanId(@Param("planId")int PlanId);
    int delByOrderId(@Param("orderId") int orderId);

    int delByOrderIdAndSeatColumnAndSeatLine(@Param("orderId") int orderId, @Param("seatColumn") int seatColumn, @Param("seatLine") int seatLine);

    int deleteByMoviePlanIds(@Param("moviePlanIds") List<Integer> moviePlanIds);



}




