package com.duplicall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description SpringBootKafkaApplicationTest
 * @Author Sean
 * @Date 2020/6/4 10:13
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootKafkaApplicationTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
