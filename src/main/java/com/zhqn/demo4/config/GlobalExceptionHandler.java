package com.zhqn.demo4.config;

import com.zhqn.demo4.ex.BaseException;
import com.zhqn.demo4.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: GlobalExceptionHandler
 * Author:   zhouquan3
 * Date:     2021/4/28 17:51
 * Description: 全局异常处理类
 *
 * @author zhouquan3
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVo<Serializable> exceptionHandler(Exception exception) {
        log.error("内部错误", exception);
        return ResultVo.error(700, "服务异常");
    }

    @ExceptionHandler(BindException.class)
    public ResultVo<Serializable> bindException(BindException exception) {
        log.info("实体绑定错误:{}", exception.getMessage());
        List<Map.Entry> bindErrors = exception.getFieldErrors().stream().map(fieldError -> {
            Map.Entry<String, Object> entry = new AbstractMap.SimpleEntry(fieldError.getField(), fieldError.getDefaultMessage());
            return entry;
        }).collect(Collectors.toList());
        return ResultVo.error(600, bindErrors);
    }

    @ExceptionHandler(BaseException.class)
    public ResultVo<Serializable> baseExceptionHandler(BaseException baseException) {
        log.info("业务异常:{}", baseException.getMessage());
        return ResultVo.error(600, baseException.getMessage());
    }
}
