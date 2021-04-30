package com.zhqn.demo4.controller;

import com.zhqn.demo4.entity.User;
import com.zhqn.demo4.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: UserController
 * Author:   zhouquan3
 * Date:     2021/4/21 17:46
 * Description: 用户接口
 * @author zhouquan3
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/findById")
    public User findById(Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public void save(User user) {
        userService.save(user);
    }

    @GetMapping("/printTime")
    public List<Object> printTime() {
        List<Object> list = new ArrayList<>();
        list.add(new Date());
        list.add(LocalDate.now());
        list.add(LocalDateTime.now());
        return list;
    }

    @GetMapping("/inputTime")
    public List<Object> inputTime(Date d1, LocalDate d2, LocalDateTime d3) {

        return Arrays.asList(d1, d2, d3);
    }

    @PostMapping("/inputTime")
    public List<Object> inputTime(@RequestBody Map<String, Object> map) {

        return Arrays.asList(map.get("d1"), map.get("d2"), map.get("d3"));
    }
}
