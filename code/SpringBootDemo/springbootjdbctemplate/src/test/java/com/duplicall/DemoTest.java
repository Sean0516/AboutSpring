package com.duplicall;

import com.duplicall.model.Student;
import com.duplicall.service.DemoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    @Autowired
    private DemoImpl demo;
    @Test
    public void list(){
        List<Student> students = demo.listStudent();
        for (Student student : students) {
            System.out.println(student.toString());
        }

        List<Student> teacher = demo.listTeacher();
        for (Student student : teacher) {
            System.out.println(student.toString());
        }
    }
    @Test
    public void queryById(){
//        System.out.println(demo.queryStudentById(1).toString());
        demo.insertTeacher(new Student("master",55));
    }
}

