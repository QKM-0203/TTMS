package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
       User getByaccounts(@Param("accounts") String accounts);

}




