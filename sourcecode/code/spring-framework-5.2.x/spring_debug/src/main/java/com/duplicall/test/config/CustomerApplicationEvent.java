package com.duplicall.test.config;

import org.springframework.context.ApplicationEvent;

/**
 * @Description CustomerApplicationEvent
 * @Author Sean
 * @Date 2022/1/21 14:55
 * @Version 1.0
 */
public class CustomerApplicationEvent extends ApplicationEvent {
	private static final long serialVersionUID=1;
	private String name;

	public CustomerApplicationEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	public CustomerApplicationEvent(Object source) {
		super(source);
	}
	public void info(){
		System.out.println("hello world");
	}
	public void say() {
		System.out.println("\"CustomerApplicationEvent\" = " + "CustomerApplicationEvent say");
	}
}
