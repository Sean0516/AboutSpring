package com.duplicall.student;

import com.duplicall.model.Student;
import com.duplicall.service.IStudent;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description StudentTest
 * @Author Sean
 * @Date 2020/5/13 16:06
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IStudent studentService;

    @Test
    public void testListStudent() {
        PageInfo<Student> studentPageInfo = studentService.listStudent();

        List<Student> list = studentPageInfo.getList();
        for (Student student : list) {
            logger.info("student info [{}]", student.toString());
        }
        logger.info("student total [{}] pages [{}] current list size [{}]", studentPageInfo.getTotal(), studentPageInfo.getPages(), list.size());
    }

    @Test
    public void testStudentAdd() {
        studentService.addStudent(new Student("user", 13));
    }
    @Test
    public void testFindStudentById(){
        Student studentById = studentService.getStudentById(1);
        logger.info(studentById.toString());
    }
    @Test
    public void testEdit(){
        studentService.editStudent(new Student(1,"first",223));

    }
    @Test
    public void testDelete(){
        studentService.deleteStudent(4);
    }

}
