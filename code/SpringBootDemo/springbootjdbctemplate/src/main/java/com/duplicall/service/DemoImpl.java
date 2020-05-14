package com.duplicall.service;

import com.duplicall.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sean
 * @description DemoImpl
 * @date 2020/5/13 22:01
 */
@Service
public class DemoImpl {
    @Autowired
    @Qualifier("studentTemplate")
    private JdbcTemplate studentTemplate;
    @Autowired
    @Qualifier("schoolTemplate")
    private JdbcTemplate schoolTemplate;

    public List<Student> listStudent() {
        return studentTemplate.queryForList("select * from t_student", Student.class, new Object[]{});
    }

    public List<Student> listTeacher() {
        return schoolTemplate.query("select * from teacher", new Object[]{}, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student queryStudentById(int id) {
        return studentTemplate.queryForObject("selcet * from t_student where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student queryTeacherById(int id) {
        return schoolTemplate.queryForObject("selcet * from teacher where id = ?", Student.class, new Object[]{id});
    }
    public void insertTeacher(Student student){
        studentTemplate.update("INSERT  INTO teacher (name ,age ) VALUE (?,?)",student.getName(),student.getAge());
    }
    public void updateTeacher(Student student){

    }
}
