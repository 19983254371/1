package com.hqyj.cyj.moudls.test.service;

import com.hqyj.cyj.moudls.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    Country mograteCountryByRedis(int countryId);
}
