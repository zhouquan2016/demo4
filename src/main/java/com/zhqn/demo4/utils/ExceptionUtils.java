package com.zhqn.demo4.utils;

import com.zhqn.demo4.ex.BaseException;

/**
 * FileName: ExceptionUtils
 * Author:   zhouquan3
 * Date:     2021/4/28 17:45
 * Description: 异常工具类
 * @author zhouquan3
 */
public class ExceptionUtils {

    /**
     * 抛出异常
     * @param err 错误提示
     */
    public static void assertThat(String err) {
        throw new BaseException(err);
    }

    /**
     * 有选择的抛出异常
     * @param expect true 不抛出异常 , false 抛出
     * @param err 错误提示
     */
    public static void assertThat(boolean expect, String err) {
        if (!expect) {
            throw new BaseException(err);
        }
    }
}
