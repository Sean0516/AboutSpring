package com.duplicall.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description AroundAspect
 * @Author Sean
 * @Date 2020/5/11 17:58
 * @Version 1.0
 */
@Component
@Aspect
public class AroundAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(" execution(public * com.duplicall.controller.AroundController..*(..))")
    public void around() {
    }

    @Around("around()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        logger.info("the name of access method is  [{}]", method.getName());
        Object[] args = joinPoint.getArgs();
        logger.info("params of access method [{}]", Arrays.toString(args));
        try {
            logger.info("前置通知结束，进入后置通知");
            proceed = joinPoint.proceed(args);
            logger.info("controller return");
        } catch (Throwable throwable) {
            logger.error("method happen error  [{}]", throwable.getMessage());
            throw throwable;
        }
        return proceed;
    }
}
