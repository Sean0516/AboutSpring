package com.duplicall.controller;

import com.duplicall.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String name;
    @Value("${app.name}")
    private String appName;

    @Autowired
    private UserConfig userConfig;

    @RequestMapping("userInfo")
    public String userInfo(){
       return userConfig.toString();
    }
    @RequestMapping("demo")
    public String demo(){
    return url +name +appName;
    }


}
