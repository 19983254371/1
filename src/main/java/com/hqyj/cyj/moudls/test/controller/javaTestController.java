package com.hqyj.cyj.moudls.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/test")
public class javaTestController {

    @GetMapping("/firstTest")
    @ResponseBody
    public String test(){
        return "this is test of my first springboot";
    }
}
