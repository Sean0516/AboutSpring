package com.duplicall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Sean
 * @description DemoImpl
 * @date 2020/5/13 22:01
 */
@Service
public class DemoImpl {
    @Autowired
    @Qualifier("userTemplate")
    private JdbcTemplate userTemplate;
    @Autowired
    @Qualifier("studentTemplate")
    private JdbcTemplate studentTemplate;

}
