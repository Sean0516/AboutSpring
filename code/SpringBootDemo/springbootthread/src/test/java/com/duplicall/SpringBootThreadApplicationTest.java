package com.duplicall;

import com.duplicall.service.DemoService;
import com.duplicall.service.SyncService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sean
 * @description
 * @date 2020/4/28 21:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootThreadApplicationTest {
    @Autowired
    private DemoService demoService;
    @Autowired
    private SyncService syncService;
    @Test
    public void testSync(){
        for (int i = 0; i < 3; i++) {
            demoService.sayHello();
            syncService.syncSayHello();
        }
    }
}
