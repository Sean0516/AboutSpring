package com.duplicall.test.config;

import org.springframework.context.LifecycleProcessor;
import org.springframework.context.support.DefaultLifecycleProcessor;

/**
 * @Description CustomLifecycle
 * @Author Sean
 * @Date 2022/1/21 15:55
 * @Version 1.0
 */
public class CustomLifecycle extends DefaultLifecycleProcessor {

	@Override
	public void start() {
		System.out.println("CustomLifecycle start");
		super.start();
	}

	@Override
	public void stop() {
		System.out.println("CustomLifecycle stop");
		super.stop();
	}
}
