package com.duplicall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Sean
 * @description
 * @date 2020/4/28 21:36
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SpringBootThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootThreadApplication.class, args);
    }
}
