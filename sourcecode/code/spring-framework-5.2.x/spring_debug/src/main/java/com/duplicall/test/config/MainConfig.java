package com.duplicall.test.config;

import com.duplicall.test.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description MainConfig
 * @Author Sean
 * @Date 2021/10/28 19:02
 * @Version 1.0
 */
@Configuration
@Import(UserSelector.class)
public class MainConfig {
	@Bean
	public Student student(){
		return new Student();
	}
}
