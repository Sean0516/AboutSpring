package com.starter.duplicall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description SeanAutoConfiguration
 * @Author Sean
 * @Date 2021/11/8 16:15
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(value = "sean.demo.name")
@EnableConfigurationProperties(SeanProperties.class)
public class SeanAutoConfiguration {
    @Autowired
    private SeanProperties seanProperties;
    @Bean
    public SeanBean seanBean(){
        return new SeanBean(seanProperties);
    }
}
