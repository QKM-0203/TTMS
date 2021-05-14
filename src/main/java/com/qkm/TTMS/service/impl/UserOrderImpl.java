package com.qkm.TTMS.service.impl;

import com.qkm.TTMS.entity.UserOrder;
import com.qkm.TTMS.mapper.UserOrderMapper;
import com.qkm.TTMS.service.UserOrderService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserOrderImpl implements UserOrderService {

    private final UserOrderMapper userOrderMapper;

    public UserOrderImpl(UserOrderMapper userOrderMapper) {
        this.userOrderMapper = userOrderMapper;
    }

    @Override
    public int saveOrder(UserOrder userOrder) {
        return userOrderMapper.insertAll(userOrder);
    }

    @Override
    public List<UserOrder> getAllByCinemaId(Long cinemaId) {
        return userOrderMapper.getAllByCinemaId(cinemaId);
    }

    @Override
    public List<UserOrder> getAllByUserId(Long userId) {
        return userOrderMapper.getAllByUserId(userId);
    }

    @Override
    public int delById(Long id) {
        return userOrderMapper.delById(id);
    }

    @Override
    public int updateOrderStatusById(String orderStatus, Long id) {
        return userOrderMapper.updateOrderStatusById(orderStatus, id);
    }

    @Override
    public int deleteByCinemaId(Long cinemaId) {
        return userOrderMapper.deleteByCinemaId(cinemaId);
    }

}
