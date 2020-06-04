package com.duplicall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description DemoImpl
 * @Author Sean
 * @Date 2020/6/4 10:24
 * @Version 1.0
 */
@Service
public class DemoImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("test-topic", "hello world" + i);
        }
    }
}
