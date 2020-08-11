package com.hqyj.cyj.moudls.test.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.dao.CityDao;
import com.hqyj.cyj.moudls.test.entity.City;
import com.hqyj.cyj.moudls.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityDao cityDao;

    @Override
    public List<City> getCityByCountryId(int countryId) {
//        return cityDao.getCityByCountryId(countryId);
        return Optional
                .ofNullable(cityDao.getCityByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<>
                (Optional
                        .ofNullable(cityDao.getCityByCountryId(countryId))
                        .orElse(Collections.emptyList()));
    }
}
