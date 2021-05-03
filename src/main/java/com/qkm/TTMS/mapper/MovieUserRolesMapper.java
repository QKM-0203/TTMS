package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.MovieUserRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.qkm.TTMS.entity.MovieUserRoles
 */
@Repository
public interface MovieUserRolesMapper extends BaseMapper<MovieUserRoles> {
    int delByUserId(@Param("userId")Long userId);
}




