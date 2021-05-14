package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.service.SeatService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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
    public Map<String, String> getSeatByRedis(Long moviePlanId) {
            Map<String, String> execute = redisTemplate.execute((RedisCallback<Map<String, String>>) con -> {
                Map<byte[], byte[]> result = con.hGetAll(String.valueOf(moviePlanId).getBytes());
                if (CollectionUtils.isEmpty(result)) {
                    return null;
                }

                Map<String, String> ans = new HashMap<>(result.size());
                for (Map.Entry<byte[], byte[]> entry : result.entrySet()) {
                    ans.put(new String(entry.getKey()), new String(entry.getValue()));
                }
                return ans;
            });
            return execute;
    }
}
