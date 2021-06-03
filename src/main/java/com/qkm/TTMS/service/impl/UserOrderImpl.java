package com.qkm.TTMS.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qkm.TTMS.entity.MovieHall;
import com.qkm.TTMS.entity.MovieUser;
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
    public List<UserOrder> getAllByCinemaId(int cinemaId,int page) {
        Page<UserOrder> movieOrderPage = new Page<>(page,5,true);
        IPage<UserOrder> userOrderIPage = userOrderMapper.selectByCinemaId(movieOrderPage, cinemaId);
        List<UserOrder> userOrderIPageList = userOrderIPage.getRecords();
        userOrderIPageList.add(new UserOrder(String.valueOf(userOrderIPage.getPages())));
        return userOrderIPageList;
    }

    @Override
    public List<UserOrder> getAllByUserId(int userId,int page) {
        Page<UserOrder> userOrderIPage = new Page<>(page,5,true);
        IPage<UserOrder> allByUserId = userOrderMapper.getAllByUserId(userOrderIPage, userId);
        List<UserOrder> allByUserIdList = allByUserId.getRecords();
        allByUserIdList.add(new UserOrder(String.valueOf(allByUserId.getPages())));
        return allByUserIdList;
    }

    @Override
    public int delById(int id) {
        return userOrderMapper.delById(id);
    }

    @Override
    public int updateOrderStatusById(String orderStatus, int id) {
        return userOrderMapper.updateOrderStatusById(orderStatus, id);
    }

    @Override
    public int deleteByCinemaId(int cinemaId) {
        return userOrderMapper.deleteByCinemaId(cinemaId);
    }

}
