package com.duplicall.service;

import com.duplicall.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        return studentTemplate.query("select * from t_student", new Object[]{}, new BeanPropertyRowMapper<>(Student.class));
    }

    public List<Student> listTeacher() {
        return schoolTemplate.query("select * from teacher", new Object[]{}, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student queryStudentById(int id) {
        return studentTemplate.queryForObject("select  * from t_student where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student queryTeacherById(int id) {
        return schoolTemplate.queryForObject("select * from teacher where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Student.class));
    }

    public Student queryTeacherPrepStatementById(int id) {
        return schoolTemplate.query("select * from teacher where id = ?", preparedStatement -> preparedStatement.setInt(1, id), new BeanPropertyRowMapper<>(Student.class)).get(0);
    }

    public void insertTeacher(Student student) {
        schoolTemplate.update("INSERT  INTO teacher (name ,age ) VALUE (?,?)", student.getName(), student.getAge());
    }

    public void updateTeacher(Student student) {
        schoolTemplate.update("update teacher set name =?, age=? where  id=?", student.getName(), student.getAge(), student.getId());
    }
    public void deleteTeacherById(int id){
        schoolTemplate.update("DELETE  FROM  teacher WHERE  id=?",id);
    }


}
