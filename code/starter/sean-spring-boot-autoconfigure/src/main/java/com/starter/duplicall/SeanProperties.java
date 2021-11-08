package com.starter.duplicall;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description SeanProperties
 * @Author Sean
 * @Date 2021/11/8 16:16
 * @Version 1.0
 */
@ConfigurationProperties("sean.demo")
public class SeanProperties {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
