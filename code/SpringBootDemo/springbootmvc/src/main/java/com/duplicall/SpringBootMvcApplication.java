package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootMvcApplication
 * @Author Sean
 * @Date 2020/5/11 10:26
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootMvcApplication {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvcApplication.class, args);
    }
}
