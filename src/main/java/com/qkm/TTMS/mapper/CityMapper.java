package com.qkm.TTMS.mapper;

import com.qkm.TTMS.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.qkm.TTMS.entity.City
 */
@Repository
public interface CityMapper extends BaseMapper<City> {
    List<City> getAllByProvinceId(@Param("provinceId")int provinceId);
}




