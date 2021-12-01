package com.duplicall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.duplicall.core.demo.entity.User;
import com.duplicall.core.demo.mapper.UserMapper;
import com.duplicall.core.demo.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;


/**
 * @Description com.duplicall.DemoTest
 * @Author Sean
 * @Date 2021/12/1 11:49
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTest {
    @Autowired
    private IUserService  userService;
    @Test
    public void test(){
//        User user = new User();
//        user.setId(1234552);
//        user.setName("SEAN");
//        user.setEmail("55555@qq.com");
//        userService.save(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByAsc("id");
        Page<User> page = userService.page(new Page<>(1, 2),queryWrapper);
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.hasNext() = " + page.hasNext());
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.getRecords().size() = " + page.getRecords().size());
    }
    @Test
    public void generator(){
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Sean") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://file"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.duplicall.core") // 设置父包名
                            .moduleName("demo") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://file")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
