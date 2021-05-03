package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.Movie;

import java.util.List;

public interface SeatSer {
   List<HallSeat> getSeatByPlanId(Long planId);
   int saveSeat(HallSeat hallSeat);

}
