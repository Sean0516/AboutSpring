package com.duplicall;
import com.duplicall.model.Student;
import com.duplicall.service.IStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description SpringBootRedisTest
 * @Author Sean
 * @Date 2020/5/27 20:49
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRedisTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IStudent studentService;
    @Test
    public void addTest(){
        studentService.add("demo",new Student(22,"Sean",33));
        studentService.add2("9527","Hello World");
    }
    @Test
    public void queryTest(){
        Student demo = studentService.getStudent("demo");
        logger.info("get demo student info [{}]",demo.toString());
    }
    @Test
    public void delete(){
        studentService.delete("demo");
        logger.info("delete key demo success ");
    }

}
