package com.qkm.TTMS.service;

import com.qkm.TTMS.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOrderSer {
    int saveOrder(UserOrder userOrder);
    List<UserOrder> getAllByCinemaId(@Param("cinemaId") Long cinemaId);
    List<UserOrder> getAllByUserId( Long userId);
    int delById(Long id);
    int updateOrderStatusById(String orderStatus, Long id);

}
