package com.duplicall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description TestXmlBeanFactory
 * @Author Sean
 * @Date 2022/1/19 9:45
 * @Version 1.0
 */
public class TestXmlBeanFactory {
	@Test
	public void testXmlBeanFactory(){
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring-bean.xml"));
		for (String beanDefinitionName : xmlBeanFactory.getBeanDefinitionNames()) {
			System.out.println("beanDefinitionName = " + beanDefinitionName);
		}
	}
}
