package com.duplicall.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description AdviceAspect
 * @Author Sean
 * @Date 2020/5/11 17:58
 * @Version 1.0
 */
@Component
@Aspect
public class AdviceAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.duplicall.controller.AdviceController..*(..))")
    public void advice() {
    }

    @Before("advice()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("method name [{}]", name);
        logger.info("params [{}]", args.toString());
        logger.info("before end and  enter controller");
    }

    @AfterReturning(pointcut = "advice()", returning = "obj")
    public void afterAdvice(Object obj) {
        logger.info("controller return msg" + obj);
    }

    @AfterThrowing(pointcut = "advice()", throwing = "t")
    public void afterThrowing(JoinPoint joinPoint, Throwable t) {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        logger.info("method name [{}]", signature.getName());
        logger.info("params [{}]", Arrays.toString(args));
        logger.error("controller happen error [{}]",t.getMessage());
    }

}
