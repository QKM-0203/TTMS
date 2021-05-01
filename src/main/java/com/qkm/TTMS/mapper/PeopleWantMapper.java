package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.PeopleWant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.PeopleWant
 */
@Repository
public interface PeopleWantMapper extends BaseMapper<PeopleWant> {
//      List<Long>  select
     List<Long>  selectMovieIdByAccounts(@Param("accounts") String accounts);

}




