package com.zhqn.demo4.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Objects;

/**
 * FileName: NumberUtils
 * Author:   zhouquan3
 * Date:     2021/4/29 14:07
 * Description: number utils
 * @author zhouquan3
 */
public class NumberUtils {
    /**
     * 将数组中重复的元素移到最后
     * @param nums 数组
     */
    public static void moveRepeat2Latest(int[] nums) {
        if (Objects.isNull(nums)) {
            return;
        }
        int startIndex = 0, endIndex = nums.length - 1;
        while (endIndex - startIndex > 1) {
            boolean repeat = false;
            int nextIndex = startIndex + 1;
            while (nextIndex <= endIndex) {
                if (nums[startIndex] == nums[nextIndex]) {
                    swap(nums, nextIndex, endIndex--);
                    repeat = true;
                }else {
                    nextIndex++;
                }
            }
            if (repeat) {
                swap(nums, startIndex, endIndex--);
            }else {
                startIndex++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = RandomUtil.randomInt(nums.length);
        }
//        System.out.println(ArrayUtil.join(nums, ","));
        long st = System.currentTimeMillis();
        moveRepeat2Latest(nums);
        System.out.println("花费" + ( System.currentTimeMillis() - st) + "ms");
//        System.out.println(ArrayUtil.join(nums, ","));
    }
}
