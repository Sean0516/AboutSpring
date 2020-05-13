package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootRestTemplateDemoApplication
 * @Author Sean
 * @Date 2020/5/13 14:13
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootRestTemplateDemoApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestTemplateDemoApplication.class, args);
    }
}
