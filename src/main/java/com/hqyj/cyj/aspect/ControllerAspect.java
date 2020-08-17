package com.hqyj.cyj.aspect;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * com.hqyj.cyj.moudls.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){}

    @Before(value = "com.hqyj.cyj.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinpoint){
        LOGGER.debug("==this is before controller==");
        ServletRequestAttributes attributeEvent=
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributeEvent.getRequest();
        LOGGER.debug("请求来源:"+request.getRemoteAddr());
        LOGGER.debug("请求URL:"+request.getRequestURI().toString());
        LOGGER.debug("请求方式:"+request.getMethod());
        LOGGER.debug("响应方法:"+ joinpoint.getSignature().getDeclaringTypeName()+"."+
                joinpoint.getSignature().getName());
        LOGGER.debug("请求参数:"+ Arrays.toString(joinpoint.getArgs()));

    }

    @Around(value ="com.hqyj.cyj.aspect.ControllerAspect.controllerPointCut()" )
    public  Object arroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("===this is around controller===");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.hqyj.cyj.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("===this is after controller===");
    }

}
