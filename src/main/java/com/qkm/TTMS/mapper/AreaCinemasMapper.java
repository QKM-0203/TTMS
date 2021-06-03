package com.qkm.TTMS.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qkm.TTMS.entity.AreaCinemas;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AreaCinemasMapper extends BaseMapper<AreaCinemas> {

    IPage<AreaCinemas> getCinemaByCinemaId(@Param("page")IPage<AreaCinemas> page, @Param("movieId") int movieId);
    AreaCinemas getAllById(@Param("id") int id);
    List<Integer> getListID();
    IPage<AreaCinemas> getAll(@Param("page") IPage<AreaCinemas> page);
    int deleteById(@Param("cinemaId") int cinemaId);
    int addMoney(@Param("money")Double money,@Param("cinemaId") int cinemaId);
    int downMoney(@Param("money")Double money,@Param("cinemaId") int cinemaId);
    IPage<AreaCinemas> getAllByAreaName(@Param("page") IPage<AreaCinemas> page,@Param("cinemaName") String cinemaName);
    IPage<AreaCinemas> getCinemasByProvinceAndCityAndArea( IPage<AreaCinemas> page,@Param("areaId") int areaId,@Param("movieId") int movieId);
    IPage<AreaCinemas> getCinemasByProvince( IPage<AreaCinemas> page,@Param("provinceId") int provinceId,@Param("movieId") int movieId);
    IPage<AreaCinemas> getCinemasByProvinceAndCity( IPage<AreaCinemas> page,@Param("cityId") int cityId,@Param("movieId") int movieId);



}




