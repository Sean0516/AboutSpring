package com.duplicall.controller;

import com.duplicall.customevent.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sean
 * @description 自定义时间调用controller
 * @date 2020/4/27 22:28
 */
@RestController
public class EventController {
    @Autowired
    private CustomEventPublisher customEventPublisher;

    @RequestMapping("eventPublish")
    public String eventPublish(){
        customEventPublisher.publish("demoEvent");
        return "event publish success ";
    }
}
