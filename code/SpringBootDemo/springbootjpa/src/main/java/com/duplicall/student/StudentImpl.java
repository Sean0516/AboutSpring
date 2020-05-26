package com.duplicall.student;

import com.duplicall.config.StudentRepository;
import com.duplicall.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description StudentImpl
 * @Author Sean
 * @Date 2020/5/25 17:23
 * @Version 1.0
 */
@Service
public class StudentImpl implements IStudent {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    @Override
    public String getStudentNameById(int id) {
        return studentRepository.getStudentNameById(id);
    }

    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void edit(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
