package com.duplicall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Sean
 * @description 异步功能
 * @date 2020/4/28 21:39
 */
@Service
public class SyncService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Async
    public void syncSayHello(){
        logger.info("thread [{}] say hello",Thread.currentThread().getName());

    }

}
