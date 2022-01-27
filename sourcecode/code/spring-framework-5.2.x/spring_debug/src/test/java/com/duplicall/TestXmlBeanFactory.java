package com.duplicall;

import com.duplicall.test.config.CustomApplicationPublish;
import com.duplicall.test.config.CustomerApplicationEvent;
import com.duplicall.test.config.EchoBeanPostProcessor;
import com.duplicall.test.model.User;
import com.duplicall.test.service.HelloTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description TestXmlBeanFactory
 * @Author Sean
 * @Date 2022/1/19 9:45
 * @Version 1.0
 */
public class TestXmlBeanFactory {
	@Test
	public void testXmlBeanFactory() {
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring-bean.xml"));
		xmlBeanFactory.addBeanPostProcessor(new EchoBeanPostProcessor());
		User user = xmlBeanFactory.getBean("user", User.class);
		System.out.println("user = " + user);
	}

	@Test
	public void testXmlPathApplicationContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean.xml");
		HelloTest hello = applicationContext.getBean("hello", HelloTest.class);
		hello.hello();


	}
}
