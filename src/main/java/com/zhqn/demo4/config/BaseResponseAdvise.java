package com.zhqn.demo4.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhqn.demo4.vo.ResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: BaseResponseAdvise
 * Author:   zhouquan3
 * Date:     2021/4/28 18:16
 * Description: 返回结果重写
 * @author zhouquan3
 */
@ControllerAdvice
public class BaseResponseAdvise implements ResponseBodyAdvice<Object> {

    @Resource
    ObjectMapper objectMapper;

    String getExecuteTypeName(MethodParameter methodParameter) {
        return methodParameter.getExecutable().getAnnotatedReceiverType().getType().getTypeName();
    }
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !GlobalExceptionHandler.class.getName().equals(getExecuteTypeName(methodParameter));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class converterClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResultVo<?> resultVo = ResultVo.success((Serializable) o);
        if (converterClass.equals(StringHttpMessageConverter.class)) {
            try {
                serverHttpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                return objectMapper.writeValueAsString(resultVo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                serverHttpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return null;
            }
        }
        return resultVo;
    }



}
