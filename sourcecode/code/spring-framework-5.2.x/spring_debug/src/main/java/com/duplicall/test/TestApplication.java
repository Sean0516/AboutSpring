package com.duplicall.test;

import com.duplicall.test.config.MainConfig;
import com.duplicall.test.config.Student;
import com.duplicall.test.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description TestApplication
 * @Author Sean
 * @Date 2021/10/20 15:33
 * @Version 1.0
 */
public class TestApplication {
	public static void main(String[] args) {
//		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean.xml");

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

		User bean = annotationConfigApplicationContext.getBean(User.class);
		System.out.println(bean);
	}
}
