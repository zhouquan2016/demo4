package com.zhqn.demo4.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: User
 * Author:   zhouquan3
 * Date:     2021/4/21 17:43
 * Description: 用户实体
 * @author zhouquan3
 */
@Data
@Entity
public class User implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private LocalDate updateTime;

    private LocalDateTime createTime;
}
