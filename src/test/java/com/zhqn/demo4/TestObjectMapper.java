package com.zhqn.demo4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhqn.demo4.entity.Menu;
import com.zhqn.demo4.vo.MenuVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: TestObjectMapper
 * Author:   zhouquan3
 * Date:     2021/4/30 15:26
 */
@SpringBootTest
public class TestObjectMapper {

    @Resource
    @Qualifier("initObjectMapper")
    ObjectMapper objectMapper;

    @Test
    void print() throws JsonProcessingException {
        MenuVo menuVo = new MenuVo();
        menuVo.setId("1");
        System.out.println(objectMapper.writeValueAsString(menuVo));
    }
}
