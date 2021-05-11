package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.HallSeat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.HallSeat
 */
@Repository
public interface HallSeatMapper extends BaseMapper<HallSeat> {
    List<HallSeat> getAllByMoviePlanId(@Param("planId")Long PlanId);

    int delByOrderId(@Param("orderId") Long orderId);

    int delByOrderIdAndSeatColumnAndSeatLine(@Param("orderId") Long orderId, @Param("seatColumn") Integer seatColumn, @Param("seatLine") Integer seatLine);

    int deleteByMoviePlanIds(@Param("moviePlanIds") List<Long> moviePlanIds);


}




