package com.duplicall.test.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description UserSelector
 * @Author Sean
 * @Date 2021/10/28 19:28
 * @Version 1.0
 */
public class UserSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {"com.duplicall.test.model.User"};
	}
}
