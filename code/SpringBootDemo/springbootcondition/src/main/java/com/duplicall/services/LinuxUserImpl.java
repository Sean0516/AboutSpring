package com.duplicall.services;

import com.duplicall.config.LinuxCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @Description LinuxUserImpl
 * @Author Sean
 * @Date 2020/5/9 17:11
 * @Version 1.0
 */
@Service
@Conditional(LinuxCondition.class)
public class LinuxUserImpl implements IUser{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void say() {
        logger.info("linux user say hello");
    }
}
