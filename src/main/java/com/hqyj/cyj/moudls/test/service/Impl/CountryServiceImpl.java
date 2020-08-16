package com.hqyj.cyj.moudls.test.service.Impl;

import com.hqyj.cyj.moudls.test.dao.CountryDao;
import com.hqyj.cyj.moudls.test.entity.Country;
import com.hqyj.cyj.moudls.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountryByRedis(int countryId) {
        Country country=countryDao.getCountryByCountryId(countryId);

        String countryKey = String.format("country%d",countryId);
        redisTemplate.opsForValue().set(countryKey,country);

        return (Country) redisTemplate.opsForValue().get(countryKey);
    }
}
