package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootAopApplication
 * @Author Sean
 * @Date 2020/5/11 16:53
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootAopApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAopApplication.class, args);
    }
}
