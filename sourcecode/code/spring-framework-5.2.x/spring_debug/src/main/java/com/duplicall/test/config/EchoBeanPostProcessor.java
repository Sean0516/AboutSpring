package com.duplicall.test.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @Description EchoBeanPostProcessor
 * @Author Sean
 * @Date 2022/1/20 17:02
 * @Version 1.0
 */
public class EchoBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor   前置处理器" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("BeanPostProcessor 后置处理器" + beanName);
		return bean;
	}
}
