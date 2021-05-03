package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.SeatSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatSerImpl implements SeatSer {
    private final HallSeatMapper hallSeatMapper;

    public SeatSerImpl(HallSeatMapper hallSeatMapper) {
        this.hallSeatMapper = hallSeatMapper;
    }

    @Override
    public List<HallSeat> getSeatByPlanId(Long planId) {
        return hallSeatMapper.getAllByMoviePlanId(planId);
    }

    @Override
    public int saveSeat(HallSeat hallSeat) {
        return hallSeatMapper.insert(hallSeat);
    }
}
