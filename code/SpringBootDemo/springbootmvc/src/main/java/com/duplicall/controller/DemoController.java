package com.duplicall.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description DemoCotroller
 * @Author Sean
 * @Date 2020/5/11 12:22
 * @Version 1.0
 */
@Controller
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("demo")
    @ResponseBody
    public String demo() {
        return "demo";
    }

    @RequestMapping("demo1")
    @ResponseBody
    public ResponseEntity demo1() {
        throw new RuntimeException("null point");
    }
}
