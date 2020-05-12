package com.duplicall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description AroundController
 * @Author Sean
 * @Date 2020/5/11 17:58
 * @Version 1.0
 */
@RestController
public class AroundController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("around")
    public String around() {
        return "around";
    }
}
