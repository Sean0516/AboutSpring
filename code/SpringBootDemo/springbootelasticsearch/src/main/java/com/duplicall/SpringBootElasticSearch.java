package com.duplicall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootElasticSearch
 * @Author Sean
 * @Date 2020/6/3 17:41
 * @Version 1.0
 */
@SpringBootApplication
public class SpringBootElasticSearch {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticSearch.class, args);
    }
}
