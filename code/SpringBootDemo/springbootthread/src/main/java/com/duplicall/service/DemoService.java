package com.duplicall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Sean
 * @description
 * @date 2020/4/28 21:41
 */
@Service
public class DemoService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    public void sayHello(){
        logger.info("thread [{}] say hello",Thread.currentThread().getName());
    }
}
