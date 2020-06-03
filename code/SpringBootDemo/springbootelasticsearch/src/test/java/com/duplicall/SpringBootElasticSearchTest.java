package com.duplicall;

import com.duplicall.model.User;
import com.duplicall.service.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description SpringBootElasticSearchTest
 * @Author Sean
 * @Date 2020/6/3 17:57
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootElasticSearchTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserImpl userImpl;

    @Test
    public void add(){
        User user=new User("demo","Sean","男");
        userImpl.add(user);
    }
}
