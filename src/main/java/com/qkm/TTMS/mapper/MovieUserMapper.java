package com.qkm.TTMS.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qkm.TTMS.entity.MovieUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieUserMapper extends BaseMapper<MovieUser> {
    MovieUser getByAccounts(@Param("accounts") String accounts);

    MovieUser getAllByAccounts(@Param("accounts") String accounts);

   List<MovieUser> getAdminByCinemaId();

   List<MovieUser> getSellByCinemaId(@Param("cinemaId") int cinemaId);

   int delById(@Param("id") int id);

    IPage<MovieUser> selectByCinemaId(@Param("page") IPage<MovieUser> page,@Param("cinemaId")int cinemaId);


    int updateIconById(@Param("id")int id,@Param("icon")String icon);
}




