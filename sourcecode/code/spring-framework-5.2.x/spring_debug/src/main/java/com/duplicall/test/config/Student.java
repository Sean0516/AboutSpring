package com.duplicall.test.config;

import org.springframework.context.annotation.Configuration;

/**
 * @Description Student
 * @Author Sean
 * @Date 2021/10/20 15:34
 * @Version 1.0
 */
@Configuration
public class Student {

	private String name;
	private int age;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
