package com.duplicall;

import com.duplicall.model.Student;
import com.duplicall.student.IStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description SpringBootJpaTest
 * @Author Sean
 * @Date 2020/5/25 17:04
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootJpaTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IStudent studentService;

    @Test
    public void queryStudent() {
        List<Student> students = studentService.listStudent();
        for (Student student : students) {
            logger.info("student info [{}]", student.toString());
        }

        logger.info("student 1 info [{}]", studentService.getStudentById(1).toString());

        logger.info("student demo info [{}]", studentService.getStudentByName("demo").toString());
        logger.info("student 3 name [{}]",studentService.getStudentNameById(3));
    }
    @Test
    public void updateStudent(){
        studentService.add(new Student("mark47",80));
        studentService.edit(new Student(1,"kk",221));
        studentService.delete(3);
    }
}

