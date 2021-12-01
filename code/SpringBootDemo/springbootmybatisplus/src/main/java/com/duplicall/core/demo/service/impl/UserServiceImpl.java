package com.duplicall.core.demo.service.impl;

import com.duplicall.core.demo.entity.User;
import com.duplicall.core.demo.mapper.UserMapper;
import com.duplicall.core.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sean
 * @since 2021-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
