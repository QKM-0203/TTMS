package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.entity.Movie;

import java.util.List;
import java.util.Map;

public interface SeatService {
   int saveSeat(HallSeat hallSeat);
   Map<String,String> getSeatByRedis(int planId);

}
