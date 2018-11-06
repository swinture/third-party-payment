package com.winture.thirdparty.payment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单号生成工具类
 * @Author: xy.zheng
 * @Date: 2018/9/29
 * @Version V1.0
 */
public class OrderNoUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     *
     * @return
     */
    public static String orderNo() {
        return orderTimeNo();
    }

    /**
     *
     * @param prefix
     * @return
     */
    public static String orderNo(String prefix) {
        return orderTimeNo(prefix);
    }

    /**
     * 生成订单号
     * @return
     */
    public static String orderTimeNo() {
        String orderNo = format.format(new Date()) + randomStr(5);
        return orderNo;
    }

    /**
     * 生成订单号
     * @return
     */
    public static String orderTimeNo(String prefix) {
        String orderNo = prefix + format.format(new Date()) + randomStr(5);
        return orderNo;
    }

    /**
     * 生成订单号
     * @param prefix 订单号前缀
     * @return
     */
    public static String orderTimeNo(String prefix, Long museId) {
        String orderNo = prefix + format.format(new Date()) + (1 + (int) (Math.random() * 10000)) + museId;
        return orderNo;
    }

    /**
     * 生成订单号
     * @param prefix 订单号前缀
     * @return
     */
    public static String orderRandomNo(String prefix, Long museId) {
        String orderNo = prefix + ((int)((Math.random()*9+1)*10000)) + museId + (System.currentTimeMillis() / 1000);
        return orderNo;
    }

    /**
     * 产生一个随机字符串
     * @param length 字符串长度
     * @return
     */
    private static String randomStr(int length) {
        Double d = Math.random();
        return d.toString().substring(2, length + 2);
    }

}
