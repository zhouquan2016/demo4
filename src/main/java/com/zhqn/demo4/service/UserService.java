package com.zhqn.demo4.service;

import com.zhqn.demo4.entity.User;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: UserService
 * Author:   zhouquan3
 * Date:     2021/4/21 17:45
 * Description: 用户service
 * @author zhouquan3
 */
public interface UserService {

    User findById(Long id);

    void save(User user);
}
