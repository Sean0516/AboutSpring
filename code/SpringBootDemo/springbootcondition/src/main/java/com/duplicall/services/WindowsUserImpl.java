package com.duplicall.services;

import com.duplicall.config.WindowsCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @Description WindowsUserImpl
 * @Author Sean
 * @Date 2020/5/9 17:09
 * @Version 1.0
 */
@Service
@Conditional(WindowsCondition.class)
public class WindowsUserImpl implements IUser {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void say() {
        logger.info("windows user say");
    }
}
