package com.duplicall;

import com.duplicall.test.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description TestAnnotationApplicaiton
 * @Author Sean
 * @Date 2022/1/20 18:02
 * @Version 1.0
 */
public class TestAnnotationApplication {
	@Test
	public void testAnnotationContext() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		for (String beanDefinitionName : annotationConfigApplicationContext.getBeanDefinitionNames()) {
			System.out.println(beanDefinitionName);
		}
	}
}
