package com.duplicall.test.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description AopConfig
 * @Author Sean
 * @Date 2022/1/21 17:02
 * @Version 1.0
 */
@Aspect
public class AopConfig {
	@Pointcut("execution(public * com.duplicall.test.service.HelloTest.hello(..))")
	public void test() {
	}

	@Before("test()")
	public void before() {
		System.out.println("before aspect");
	}

	@After("test()")
	public void after() {
		System.out.println("after aspect ");
	}

	@Around("test()")
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("around before aspect");
		Object proceed = null;
		try {
			proceed = joinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("around after aspect");
		return proceed;
	}

}
