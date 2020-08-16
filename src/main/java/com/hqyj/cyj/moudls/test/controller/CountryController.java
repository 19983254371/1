package com.hqyj.cyj.moudls.test.controller;


import com.hqyj.cyj.moudls.test.entity.Country;
import com.hqyj.cyj.moudls.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryService countryService;

    /**
     * 127.0.0.1/api/countries/522 ---- get
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
    }

    /**
     * 127.0.0.1/api/redis/country/522----get
     * */

    @GetMapping("/redis/country/{countryId}")
    public  Country mograteCountryByRedis(int countryId){
        return countryService.mograteCountryByRedis(countryId);
    }
}
