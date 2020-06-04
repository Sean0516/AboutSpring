package com.duplicall.contorller;

import com.duplicall.service.DemoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description DemoController
 * @Author Sean
 * @Date 2020/6/4 10:36
 * @Version 1.0
 */
@RestController
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DemoImpl demoService;

    @RequestMapping("demo")
    public void send() {
        demoService.send();
    }
}
