package com.duplicall.service;

import com.duplicall.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description StudentImpl
 * @Author Sean
 * @Date 2020/5/27 20:54
 * @Version 1.0
 */
@Service
public class StudentImpl implements IStudent {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(String key, Student student) {
        redisTemplate.opsForValue().set(key, student);
    }

    @Override
    public void add2(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Student getStudent(String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
