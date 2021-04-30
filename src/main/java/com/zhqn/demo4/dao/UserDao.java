package com.zhqn.demo4.dao;

import com.zhqn.demo4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: UserDao
 * Author:   zhouquan3
 * Date:     2021/4/21 17:44
 * Description: 用户dao
 * @author zhouquan3
 */
public interface UserDao extends JpaRepository<User, Long> {

}
