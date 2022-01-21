package com.duplicall.test.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Description CustomApplicaitonEvent
 * @Author Sean
 * @Date 2022/1/21 14:51
 * @Version 1.0
 */
public class CustomApplicationListener implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof CustomerApplicationEvent) {
			CustomerApplicationEvent customerApplicationEvent = (CustomerApplicationEvent) event;
			customerApplicationEvent.say();
		}
	}
}
