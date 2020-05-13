package com.duplicall;

import com.duplicall.service.ClientDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description SpringBootRestTemplateDemoApplicationTest
 * @Author Sean
 * @Date 2020/5/13 14:14
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestTemplateDemoApplicationTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ClientDemo clientDemo;

    @Test
    public void testGetDemo(){
        clientDemo.getDemo("sean");
        clientDemo.getDemo1("master");
    }
    @Test
    public void testPostDemo(){
        clientDemo.postDemo("sean");
        clientDemo.postDemo1("demo");
    }
    @Test
    public void testPutDemo(){
        clientDemo.putDemo("Sean");
    }
    @Test
    public void testDeleteDemo(){
        clientDemo.deleteDemo("Sean");
    }
}
