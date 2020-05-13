package com.duplicall;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description SpringBootMybatis
 * @Author Sean
 * @Date 2020/5/13 15:32
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.duplicall.mapper")
public class SpringBootMybatis {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatis.class,args);
    }
}
