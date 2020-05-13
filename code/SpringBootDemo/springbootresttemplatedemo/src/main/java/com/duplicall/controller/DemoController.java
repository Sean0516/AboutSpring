package com.duplicall.controller;

import com.duplicall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Description DemoController
 * @Author Sean
 * @Date 2020/5/13 11:22
 * @Version 1.0
 */
@RestController
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("getDemo")
    public String getDemo(String name) {
        return "hello" + name;
    }

    @GetMapping("demo/{name}")
    public String demo(@PathVariable(name = "name") String name) {
        return "demo" + name;
    }

    @PostMapping("postDemo")
    public String postDemo(String name) {
        return "hello" + name;
    }

    @PostMapping("postDemo1")
    public User postDemo1(String name, @RequestBody User user) {
        logger.info("name [{}]  user [{}]", name, user.toString());
        return user;
    }

    @PutMapping("putDemo")
    public void putDemo(String name) {
        logger.info("put demo name [{}]", name);
    }

    @DeleteMapping("deleteDemo")
    public void deleteDemo(String name) {
        logger.info("delete name [{}]", name);
    }
}
