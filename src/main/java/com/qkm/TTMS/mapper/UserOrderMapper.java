package com.qkm.TTMS.mapper;
import java.util.List;

import com.qkm.TTMS.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.UserOrder
 */
@Repository
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    List<UserOrder> getAllByCinemaId(@Param("cinemaId") Long cinemaId);

    List<UserOrder> getAllByUserId(@Param("userId") Long userId);

    int delById(@Param("id") Long id);

    int updateOrderStatusById(@Param("orderStatus") String orderStatus, @Param("id") Long id);
}




