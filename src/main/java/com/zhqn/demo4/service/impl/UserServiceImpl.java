package com.zhqn.demo4.service.impl;

import com.zhqn.demo4.dao.UserDao;
import com.zhqn.demo4.entity.User;
import com.zhqn.demo4.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: UserServiceImpl
 * Author:   zhouquan3
 * Date:     2021/4/21 17:48
 * Description: user service impl
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
