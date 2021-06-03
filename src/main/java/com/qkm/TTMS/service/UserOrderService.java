package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderService {
    int saveOrder(UserOrder userOrder);
    List<UserOrder> getAllByCinemaId(int cinemaId,int page);
    List<UserOrder> getAllByUserId( int userId,int page);
    int delById(int id);
    int updateOrderStatusById(String orderStatus, int id);
    int deleteByCinemaId( int cinemaId);
}
