package com.qkm.TTMS.mapper;
import java.util.List;

import com.qkm.TTMS.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    List<UserOrder> getAllByCinemaId(@Param("cinemaId") int cinemaId);

    List<UserOrder> getAllByUserId(@Param("userId") int userId);

    int delById(@Param("id") int id);

    int updateOrderStatusById(@Param("orderStatus") String orderStatus, @Param("id") int id);

    int deleteByCinemaId(@Param("cinemaId") int cinemaId);

    int insertAll(UserOrder userOrder);
}




