package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.PeopleWant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PeopleWantMapper extends BaseMapper<PeopleWant> {
     List<Integer>  selectMovieIdByAccounts(@Param("accounts") String accounts);

}




