package com.zhqn.demo4;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C), 2015-2021, 易车
 * FileName: Ball
 * Author:   zhouquan3
 * Date:     2021/4/23 14:51
 * Description:
 */
public class Ball {

    private int qianSize, qianLen, houSize, houLen;

    private int[] qian, hou;

    public Ball(int qianSize, int qianLen, int houSize, int houLen) {
        this.qianSize = qianSize;
        this.qianLen = qianLen;
        this.houSize = houSize;
        this.houLen = houLen;
        this.qian = randomBall(qianSize, qianLen);
        this.hou = randomBall(houSize, houLen);
    }

    private int[] randomBall(int size, int len) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; ++i ) {
            arr[i] = i + 1;
        }
        Random random = new Random();
        int[] randomArr = new int[len];
        int randomIndex;
        for (int i = 0; i < len; ++i) {
            randomIndex = i + random.nextInt(arr.length - i);
            randomArr[i] = arr[randomIndex];
            swap(i, randomIndex, arr);

        }
        Arrays.sort(randomArr);
        return randomArr;
    }

    private void swap(int i, int j, int[] arr) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private String print(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 10) {
                sb.append(" ");
            }
            sb.append(arr[i]);
            if (arr.length - 1 != i) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return print(this.qian) + "\t" + print(this.hou);
    }

    public boolean sameTo(Ball ball) {
        if (this.qian.length != ball.qian.length || this.hou.length != ball.hou.length) {
            return false;
        }
        for (int i = 0; i < this.qian.length; i++) {
            if (this.qian[i] != ball.qian[i]) {
                return false;
            }
        }
        return Ball.sameTo(this.qian, ball.qian) && Ball.sameTo(this.hou, ball.hou);
    }

    private static boolean sameTo(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        Ball shuang = new Ball(33, 6, 16, 1);
//        System.out.println("双色球:" + shuang);
        Ball da = new Ball(35, 5, 12, 2);
        System.out.println("大色球:" + da);
    }
}
