package com.qkm.TTMS.service.impl;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.SeatService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SeatServiceImpl implements SeatService {
    private final RedisTemplate<String,Object> redisTemplate;
    private final HallSeatMapper hallSeatMapper;

    public SeatServiceImpl(HallSeatMapper hallSeatMapper, RedisTemplate<String, Object> redisTemplate) {
        this.hallSeatMapper = hallSeatMapper;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public int saveSeat(HallSeat hallSeat) {
        return hallSeatMapper.insert(hallSeat);
    }

    @Override
    public Map<String, String> getSeatByRedis(int moviePlanId) {
        return (Map<String, String>) redisTemplate.opsForValue().get(String.valueOf(moviePlanId));

    }
}
