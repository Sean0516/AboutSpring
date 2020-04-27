package com.duplicall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventConfig implements ApplicationListener<ApplicationEvent> {
    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextStartedEvent) {
            LOGGER.info("spring  happen start work");
        } else if (applicationEvent instanceof ContextRefreshedEvent) {
            //一般来说，可以使用ContextRefreshedEvent 在程序启动的时候运行一些需要程序运行的方法，例如配置文件初始化
            if (((ContextRefreshedEvent) applicationEvent).getApplicationContext().getParent() == null) {
                LOGGER.info("spring happen  refresh event ");
            }
        } else if (applicationEvent instanceof ContextStoppedEvent) {
            LOGGER.info("spring happen stop event ");
        } else if (applicationEvent instanceof ContextClosedEvent) {
            LOGGER.info("spring happen close  event ");
        } else {
            LOGGER.info("spring  happen other  event " + applicationEvent.getClass().getSimpleName());
        }
    }
}
