package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootSwaggerApplication
 * @Author Sean
 * @Date 2020/5/12 15:49
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootSwaggerApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwaggerApplication.class, args);
    }
}
