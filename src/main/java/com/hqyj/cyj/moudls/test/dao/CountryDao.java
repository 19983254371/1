package com.hqyj.cyj.moudls.test.dao;


import com.hqyj.cyj.moudls.test.entity.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CountryDao {

    @Select("select * from m_country where country_id = #{countryId} ")
    Country getCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name = #{countryName} ")
    Country getCountryByCountryName(String countryName);
}
