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
    MovieUser getByAccounts(@Param("accounts") String accounts);
    MovieUser getAllByAccounts(@Param("accounts") String accounts);
   List<MovieUser> getAdminByCinemaId(@Param("cinemaId") Long cinemaId);
   List<MovieUser> getSellByCinemaId(@Param("cinemaId") Long cinemaId);
    int delById(@Param("id") Long id);
}




