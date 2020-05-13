package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootCustomWebSocketApplication
 * @Author Sean
 * @Date 2020/5/13 9:35
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootCustomWebSocketApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCustomWebSocketApplication.class, args);
    }
}
