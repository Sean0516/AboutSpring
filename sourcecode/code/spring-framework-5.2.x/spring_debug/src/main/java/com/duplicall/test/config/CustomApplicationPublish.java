package com.duplicall.test.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @Description CustomApplicationPublish
 * @Author Sean
 * @Date 2022/1/21 15:21
 * @Version 1.0
 */
public class CustomApplicationPublish implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public ApplicationEventPublisher getApplicationEventPublisher() {
		return applicationEventPublisher;
	}
}
