package com.duplicall.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Sean
 * @description 定时任务
 * @date 2020/4/28 21:51
 */
@Component
public class ScheduledTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(initialDelay = 1000, fixedDelay = 3000)
    public void sayHelloDelay() {
        logger.info("say hello by delay");

    }

    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void sayHelloRate() {
        logger.info("say hello by rate");
    }
}
