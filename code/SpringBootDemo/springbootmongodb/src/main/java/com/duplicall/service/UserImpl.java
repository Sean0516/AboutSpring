package com.duplicall.service;

import com.duplicall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description UserImpl
 * @Author Sean
 * @Date 2020/6/3 17:18
 * @Version 1.0
 */
@Service
public class UserImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getAllUser() {
        return mongoTemplate.findAll(User.class);
    }

    public User getUserByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), User.class);
    }

    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    public void update(User user) {
        mongoTemplate.updateMulti(new Query(Criteria.where("id").is(user.getId())), Update.update("name", user.getName()), User.class);
    }

    public void removeByName(String userName) {
        mongoTemplate.remove(new Query(Criteria.where("name").is(userName)), User.class);
    }
}
