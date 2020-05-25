package com.duplicall.config;

import com.duplicall.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Description StudentRepository
 * @Author Sean
 * @Date 2020/5/25 17:22
 * @Version 1.0
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getStudentByName(String name);
    @Query(value = "select stu.name from t_student stu where stu.id = :id")
    String getStudentNameById(@Param("id") int id);
}
