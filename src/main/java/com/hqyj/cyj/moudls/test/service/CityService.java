package com.hqyj.cyj.moudls.test.service;

import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCityByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);
}
