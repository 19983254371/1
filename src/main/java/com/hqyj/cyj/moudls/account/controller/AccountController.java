package com.hqyj.cyj.moudls.account.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }
}