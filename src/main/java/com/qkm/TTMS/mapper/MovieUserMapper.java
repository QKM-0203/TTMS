package com.qkm.TTMS.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkm.TTMS.entity.MovieUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieUser
 */
@Repository
public interface MovieUserMapper extends BaseMapper<MovieUser> {
    MovieUser getByaccounts(@Param("accounts") String accounts);

   Long getCinemaIdByAccounts(@Param("accounts") String accounts);
}




