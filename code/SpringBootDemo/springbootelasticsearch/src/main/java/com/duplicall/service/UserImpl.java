package com.duplicall.service;

import com.duplicall.config.UserRepository;
import com.duplicall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description UserImpl
 * @Author Sean
 * @Date 2020/6/3 17:51
 * @Version 1.0
 */
@Service
public class UserImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    public void add(User user) {
        logger.info("add user [{}]", user.toString());
        userRepository.save(user);
    }

    public void delete(String id) {
        logger.info("delete user [{}]", id);
        userRepository.deleteUserById(id);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User queryUserByName(String id) {
        return userRepository.queryUserById(id);
    }

    public List<User> listUser() {
        return (List<User>) userRepository.findAll();
    }

}
