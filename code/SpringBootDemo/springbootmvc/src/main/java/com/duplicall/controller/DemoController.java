package com.duplicall.controller;

import com.duplicall.model.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description DemoCotroller
 * @Author Sean
 * @Date 2020/5/11 12:22
 * @Version 1.0
 */
@RestController
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @RequestMapping("xmlDemo")
    public Demo xmlDemo() {
        return new Demo("sean", "boy");
    }

    @RequestMapping("demo1")
    public ResponseEntity demo1() {
        throw new RuntimeException("null point");
    }

}
