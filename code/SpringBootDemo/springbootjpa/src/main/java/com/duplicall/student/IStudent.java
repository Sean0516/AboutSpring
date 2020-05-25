package com.duplicall.student;

import com.duplicall.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description IStudent
 * @Author Sean
 * @Date 2020/5/25 17:19
 * @Version 1.0
 */
public interface IStudent {
    /**
     * 获取所有的 student
     * @return
     */
    List<Student> listStudent();
    Student getStudentById(int id);
    Student getStudentByName(String name);
    String  getStudentNameById(int id);
    void add(Student student);
    void edit(Student student);
    void  delete(int id);

}
