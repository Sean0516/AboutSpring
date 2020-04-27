package com.duplicall.customevent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String eventName;

    public CustomEvent(Object source, String eventName) {
        super(source);
        logger.info("event name [{}] class name [{}]", eventName, source);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
