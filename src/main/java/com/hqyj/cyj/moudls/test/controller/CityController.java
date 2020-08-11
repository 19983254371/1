package com.hqyj.cyj.moudls.test.controller;


import com.github.pagehelper.PageInfo;
import com.hqyj.cyj.moudls.common.vo.SearchVo;
import com.hqyj.cyj.moudls.test.entity.City;
import com.hqyj.cyj.moudls.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId){
        return cityService.getCityByCountryId(countryId);
    }

    @PostMapping(value = "/cities/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@PathVariable int countryId, @RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(countryId,searchVo);
    }
}
