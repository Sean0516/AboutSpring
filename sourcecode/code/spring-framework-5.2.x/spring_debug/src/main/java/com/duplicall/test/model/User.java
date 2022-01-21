package com.duplicall.test.model;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @Description User
 * @Author Sean
 * @Date 2021/10/20 15:42
 * @Version 1.0
 */
public class User  implements  BeanFactoryPostProcessor {
	private String name;
	private String local;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println(" BeanFactoryPostProcessor "+ "user ");
	}
}
