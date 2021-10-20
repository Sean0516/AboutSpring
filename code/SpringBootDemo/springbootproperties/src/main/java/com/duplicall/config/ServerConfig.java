package com.duplicall.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @Description ServerProperteis
 * @Author Sean
 * @Date 2021/6/4 17:23
 * @Version 1.0
 */
@Configuration
public class ServerConfig {
    @Bean
    public ServerProperties serverProperties(){
        ServerProperties serverProperties = new ServerProperties();
        serverProperties.getTomcat().setBasedir(new File("D:/demo"));
        return serverProperties;
    }
}
