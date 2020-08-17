package com.hqyj.cyj.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    private final static Logger LOGGER= LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(com.hqyj.cyj.aspect.ServiceAnnotation)")
    @Order(2)
    public  void  servicePointCut(){}

    @Before(value = "com.hqyj.cyj.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinpoint){
        LOGGER.debug("==this is before service==");
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

    @Around(value ="com.hqyj.cyj.aspect.ServiceAspect.servicePointCut()" )
    public  Object arroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("===this is around service===");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.hqyj.cyj.aspect.ServiceAspect.servicePointCut()")
    public void afterservice(){
        LOGGER.debug("===this is after service===");
    }
}
