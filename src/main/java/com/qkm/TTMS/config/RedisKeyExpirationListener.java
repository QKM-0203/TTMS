package com.qkm.TTMS.config;

import com.qkm.TTMS.entity.HallSeat;
import com.qkm.TTMS.mapper.HallSeatMapper;
import com.qkm.TTMS.mapper.UserOrderMapper;
import com.qkm.TTMS.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private final SeatService seatSer;
    private final RedisTemplate<String,Object> redisTemplate;
    private final HallSeatMapper hallSeatMapper;
    public RedisKeyExpirationListener(RedisMessageListenerContainer container, UserOrderMapper userOrderMapper, HallSeatMapper hallSeatMapper, SeatService seatSer, RedisTemplate<String, Object> redisTemplate) {
        super(container);
        this.userOrderMapper = userOrderMapper;
        this.hallSeatMapper = hallSeatMapper;
        this.seatSer = seatSer;
        this.redisTemplate = redisTemplate;
    }

    private final UserOrderMapper userOrderMapper;
    /**
     * 针对redis数据失效事件，进行数据处理
     * @param
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key=message.toString();//生效的key
        if (key!=null && key.startsWith("order")){//从失效key中筛选代表订单失效的key
            //截取订单号，查询订单，如果是未支付状态则取消订单
            String orderNo=key.substring(5);

            //删除订单
            userOrderMapper.delById(Integer.parseInt(orderNo));

            //取消座位
            List<HallSeat> allByOrderId = hallSeatMapper.getAllByOrderId(Integer.parseInt(orderNo));
            Map<String, String> seatByRedis = seatSer.getSeatByRedis(allByOrderId.get(0).getMoviePlanId());
            hallSeatMapper.delByOrderId(Integer.parseInt(orderNo));
            for (HallSeat seat : allByOrderId) {
                seatByRedis.put((seat.getSeatLine()) + "," + String.valueOf(seat.getSeatColumn()),"0" );
            }
            redisTemplate.opsForValue().set(String.valueOf(allByOrderId.get(0).getMoviePlanId()), seatByRedis);
            System.out.println("订单号为："+orderNo+"的订单超时未支付，取消订单");
        }
    }
}
