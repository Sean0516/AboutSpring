package com.duplicall.config;

import com.duplicall.handler.TestWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description WebSocketConfig
 * @Author Sean
 * @Date 2020/5/13 10:07
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//        设置url  和handler
        webSocketHandlerRegistry.addHandler(testWebSocketHandler(), "/test").setAllowedOrigins("*");
    }

    /**
     * 注入自定义的handler
     * @return
     */
    @Bean
    public TestWebSocketHandler testWebSocketHandler() {
        return new TestWebSocketHandler();
    }

}
