package com.duplicall.test.config;

import com.duplicall.test.model.User;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description UserBeanDefinitionRegister
 * @Author Sean
 * @Date 2021/10/28 19:32
 * @Version 1.0
 */
public class UserBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		//创建BeanDefinition
		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(User.class);
		System.out.println("start register user ");
		// 注册Bean Definition
		registry.registerBeanDefinition("user",genericBeanDefinition);
	}
}
