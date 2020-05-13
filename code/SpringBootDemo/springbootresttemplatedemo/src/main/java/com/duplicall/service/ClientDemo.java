package com.duplicall.service;

import com.duplicall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Description ClientDemo
 * @Author Sean
 * @Date 2020/5/13 11:15
 * @Version 1.0
 */
@Service
public class ClientDemo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;

    public void getDemo(String name) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8080/getDemo?name={name}", String.class, map);
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8080/getDemo?name={name}", String.class, name);
        logger.info("response is [{}]", forEntity.getBody());
    }

    public void getDemo1(String name) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8080/demo/{1}", String.class, name);
        logger.info("response is [{}]", forEntity.getBody());
    }

    public void postDemo(String name) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/postDemo?name={name}", null, String.class, map);
        logger.info("response is [{}]", responseEntity.getBody());
    }

    public void postDemo1(String name) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        User user = new User("master", "boys");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://127.0.0.1:8080/postDemo1?name={name}", user, User.class, map);
        logger.info("response is [{}]", responseEntity.getBody().toString());
    }

    public void putDemo(String name) {
        restTemplate.put("http://127.0.0.1:8080/putDemo?name={name}", String.class, name);
    }

    public void deleteDemo(String name) {
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        restTemplate.delete("http://127.0.0.1:8080/deleteDemo?name={name}", map);
    }

}
