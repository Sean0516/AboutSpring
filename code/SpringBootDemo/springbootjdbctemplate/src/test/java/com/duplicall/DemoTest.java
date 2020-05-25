package com.duplicall;

import com.duplicall.model.Student;
import com.duplicall.service.DemoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Sean
 * @description DemoTest
 * @date 2020/5/14 21:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DemoImpl demo;

    @Test
    public void list() {
        List<Student> students = demo.listStudent();
        for (Student student : students) {
            logger.info("student info [{}]",student.toString());
        }

        List<Student> teacher = demo.listTeacher();
        for (Student student : teacher) {
            logger.info("teacher info [{}]" ,student.toString());
        }
    }

    @Test
    public void queryById() {
        logger.info("query student [{}] info [{}]",1,demo.queryStudentById(1).toString());
        logger.info("query teacher [{}] info [{}]",1,demo.queryTeacherPrepStatementById(1).toString());
    }
    @Test
    public void insertTeacher(){
        logger.info("insert info teacher to db ");
        demo.insertTeacher(new Student("Mark47",22));
        for (Student student : demo.listTeacher()) {
            logger.info("list teacher [{}]",student.toString());
        }
    }
    @Test
    public void testUpdateTeacher(){
        logger.info("update teacher mark47 info");
        demo.updateTeacher(new Student(2,"Mark90",25));
        for (Student student : demo.listTeacher()) {
            logger.info("list teacher [{}]",student.toString());
        }
    }
    @Test
    public void testDeleteTeacher(){
        demo.deleteTeacherById(1);
        for (Student student : demo.listTeacher()) {
            logger.info("list teacher [{}]",student.toString());
        }
    }
}

