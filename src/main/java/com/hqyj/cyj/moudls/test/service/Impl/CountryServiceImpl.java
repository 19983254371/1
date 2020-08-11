package com.hqyj.cyj.moudls.test.service.Impl;

import com.hqyj.cyj.moudls.test.dao.CountryDao;
import com.hqyj.cyj.moudls.test.entity.Country;
import com.hqyj.cyj.moudls.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
