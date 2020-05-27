package com.duplicall.service;

import com.duplicall.model.Student;

import java.util.List;

/**
 * @Description IStudent
 * @Author Sean
 * @Date 2020/5/27 20:51
 * @Version 1.0
 */
public interface IStudent {
    void add(String key, Student student);

    void add2(String key, String value);

    Student getStudent(String key);

    void delete(String key);
}
