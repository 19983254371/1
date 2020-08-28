package com.hqyj.cyj.moudls.test.controller;


import com.hqyj.cyj.moudls.test.vo.Test2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/test1")
public class javaTestController {


    private final static Logger LOGGER = LoggerFactory.getLogger(javaTestController.class);

    @GetMapping("/firstTest")
    @ResponseBody
    public String test(){
        return "this is test of my first springboot";
    }

    @Autowired
    private Test2 test;

    @GetMapping("test2")
    @ResponseBody
    public void test2(){

    }
    @GetMapping("/logTest")
    @ResponseBody
    public String logTest() {
        LOGGER.trace("This is trace log");
        LOGGER.debug("This is debug log");
        LOGGER.info("This is info log");
        LOGGER.warn("This is warn log");
        LOGGER.error("This is error log");
        return "This is log test";
    }
}
