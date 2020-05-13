package com.duplicall.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Sean
 * @description DateSourceConfig
 * @date 2020/5/13 21:46
 */
@Configuration
public class DateSourceConfig {
    @Bean(name = "user")
    @Primary
    public DataSource dataSource() {
        return getDataSource("jdbc:mysql://localhost:3306/test");
    }

    @Bean(name = "userTemplate")
    @Primary
    public JdbcTemplate jdbcTemplate(@Qualifier("user") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "school")
    public DataSource schoolDataSource() {
        return getDataSource("jdbc:mysql://localhost:3306/school");
    }

    @Bean(name = "studentTemplate")
    public JdbcTemplate schoolJdbcTemplate(@Qualifier("school") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    private DataSource getDataSource(String s) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username("root");
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.password("123456");
        dataSourceBuilder.url(s);
        dataSourceBuilder.type(com.zaxxer.hikari.HikariDataSource.class);
        return dataSourceBuilder.build();
    }

}
