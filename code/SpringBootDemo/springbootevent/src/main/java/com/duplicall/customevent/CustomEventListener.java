package com.duplicall.customevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(CustomEvent customEvent) {

        if ("demo" == customEvent.getEventName()) {
            logger.info("demo event is happen");
        }else {
            logger.info("event name [{}] happen",customEvent.getEventName());
        }
    }
}
