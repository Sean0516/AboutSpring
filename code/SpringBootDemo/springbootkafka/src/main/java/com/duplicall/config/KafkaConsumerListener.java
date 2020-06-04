package com.duplicall.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description KafkaConsumerConfig
 * @Author Sean
 * @Date 2020/6/4 10:10
 * @Version 1.0
 */
@Component
public class KafkaConsumerListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "test-topic")
    public void msg(String msg) {
        logger.info("rev msg [{}]", msg);
    }
}
