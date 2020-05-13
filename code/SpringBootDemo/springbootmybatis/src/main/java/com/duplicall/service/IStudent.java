package com.duplicall.service;

import com.duplicall.model.Student;
import com.github.pagehelper.PageInfo;

/**
 * @Description IStudent
 * @Author Sean
 * @Date 2020/5/13 15:36
 * @Version 1.0
 */
public interface IStudent {
    /**
     * list student
     *
     * @return
     */
    PageInfo<Student> listStudent();

    /**
     * 根据id 获取student信息
     *
     * @param id
     * @return
     */
    Student getStudentById(int id);

    void addStudent(Student student);

    void editStudent(Student student);

    void deleteStudent(int id);

}
