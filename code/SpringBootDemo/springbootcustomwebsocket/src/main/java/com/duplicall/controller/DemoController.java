package com.duplicall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description DemoController
 * @Author Sean
 * @Date 2020/5/13 10:11
 * @Version 1.0
 */
@Controller
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("demo")
    public String demo() {
        return "demo";
    }
}
