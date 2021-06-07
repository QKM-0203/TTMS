package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qkm.TTMS.entity.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.Area
 */
@Repository
public interface AreaMapper extends BaseMapper<Area> {
    List<Area> getAreaByCityId(@Param("cityId")int cityId);
}




