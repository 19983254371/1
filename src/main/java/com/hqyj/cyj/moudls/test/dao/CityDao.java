package com.hqyj.cyj.moudls.test.dao;


import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {
    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCityByCountryId(int countryId);

}
