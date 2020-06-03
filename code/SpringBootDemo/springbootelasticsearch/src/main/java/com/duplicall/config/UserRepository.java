package com.duplicall.config;

import com.duplicall.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Description UserRepository
 * @Author Sean
 * @Date 2020/6/3 17:47
 * @Version 1.0
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User, String> {
    /**
     * 根据id 查询user
     * @param id
     * @return
     */
    User queryUserById(String id);

    /**
     * 根据id 查询user
     * @param id
     */
    void deleteUserById(String id);
}
