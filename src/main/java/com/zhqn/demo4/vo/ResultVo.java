package com.zhqn.demo4.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: ResultVo
 * Author:   zhouquan3
 * Date:     2021/4/28 17:50
 * Description: 返回结果
 * @author zhouquan3
 */
@Data
public class ResultVo<T extends Serializable> implements Serializable{

    private String errorCode;

    private Object errMsg;

    private T result;

    /**
     * 错误类返回对象
     * @param errorCode 错误编码
     * @param errMsg 错误信息
     * @return 错误信息
     */
    public static ResultVo<Serializable> error(String errorCode, Object errMsg) {
        ResultVo<Serializable> resultVo = new ResultVo<>();
        resultVo.setResult(null);
        resultVo.setErrorCode(errorCode);
        resultVo.setErrMsg(errMsg);
        return resultVo;
    }


    public static ResultVo<Serializable> error(Integer errorCode, Object errMsg) {
        return error(String.valueOf(errorCode), errMsg);
    }

    /**
     * 没有错误返回对象
     * @param result 返回对象
     * @return 返回信息
     */
    public static <T extends Serializable> ResultVo<T> success(T result) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setResult(result);
        return resultVo;
    }
}
