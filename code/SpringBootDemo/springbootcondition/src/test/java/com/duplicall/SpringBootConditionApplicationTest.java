package com.duplicall;

import com.duplicall.services.IUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description SpringBootConditionApplicationTest
 * @Author Sean
 * @Date 2020/5/9 17:14
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootConditionApplicationTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUser userService;
    @Test
    public void userTest(){
        userService.say();
    }
}
