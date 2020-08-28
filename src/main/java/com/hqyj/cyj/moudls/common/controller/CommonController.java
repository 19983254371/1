package com.hqyj.cyj.moudls.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/dashboard")
	public String dashboardPage() {
		return "index";
	}
	
	@GetMapping("/403")
	public String errorPageFor403() {
		return "index";
	}
}
