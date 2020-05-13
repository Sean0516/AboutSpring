package com.duplicall.service;

import com.duplicall.mapper.StudentMapper;
import com.duplicall.model.Student;
import com.duplicall.model.StudentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description StudentImpl
 * @Author Sean
 * @Date 2020/5/13 15:39
 * @Version 1.0
 */
@Service
public class StudentImpl implements IStudent {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> listStudent() {
        PageHelper.startPage(0, 3);
        List<Student> students = studentMapper.selectByExample(new StudentExample());
        return new PageInfo<>(students);
    }

    @Override
    public Student getStudentById(int id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.insertSelective(student);
    }

    @Override
    public void editStudent(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentMapper.deleteByPrimaryKey(id);
    }


}
