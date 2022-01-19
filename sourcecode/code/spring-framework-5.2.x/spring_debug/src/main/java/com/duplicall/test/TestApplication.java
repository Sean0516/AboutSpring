package com.duplicall.test;

import com.duplicall.test.config.MainConfig;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description TestApplication
 * @Author Sean
 * @Date 2021/10/20 15:33
 * @Version 1.0
 */
public class TestApplication {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean.xml");

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		for (String beanDefinitionName : annotationConfigApplicationContext.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}
}
