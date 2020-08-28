package com.hqyj.cyj.moudls.common.controller;

import com.hqyj.cyj.moudls.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

	@ExceptionHandler(value=AuthorizationException.class)
	@ResponseBody
	public Result<String> errorPage403() {
		return new Result<String>(Result.ResultStatus.FAILD.status, "", "/common/403");
	}
}
