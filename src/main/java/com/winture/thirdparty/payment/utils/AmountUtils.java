package com.winture.thirdparty.payment.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @Author: swinture
 * @Date: 2018/11/5
 * @Version V1.0
 */
public class AmountUtils {

    /**
     * 分转元
     * @param centAmount
     * @param separate 分隔符
     * @return
     */
    public static String centToYuan(long centAmount, boolean separate) {
        if (separate) {
            return centToYuanWithSeparator(centAmount);
        }

        return centToYuan(centAmount);
    }

    /**
     * 分转元
     * @param centAmount
     * @return
     */
    public static String centToYuan(long centAmount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String amount = decimalFormat.format(centAmount * 0.01);

        return amount;
    }

    /**
     *
     * @param centAmount
     * @return
     */
    public static String centToYuanWithSeparator(long centAmount) {
        String amount = "";
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        try {
            Number number = numberFormat.parse(centAmount + "");
            double temp = number.doubleValue() / 100;
            numberFormat.setMaximumFractionDigits(2);
            amount = numberFormat.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return amount;
    }

    /**
     * 元转分
     * @param amount
     * @param separate
     * @return
     */
    public static String yuanToCent(String amount, boolean separate) {
        if (separate) {
            return yuanToCentWithSeparator(amount);
        }

        return yuanToCent(amount);
    }

    /**
     *
     * @param amount
     * @return
     */
    public static String yuanToCent(double amount) {
        return yuanToCent(amount + "");
    }

    /**
     * 元转分
     * @param amount
     * @return
     */
    public static String yuanToCent(String amount) {
        if (amount == null || "".equals(amount)) {
            return "0";
        }

        try {
            BigDecimal bigDecimal = new BigDecimal(amount);
            Integer centAmount = bigDecimal.multiply(new BigDecimal(100)).intValue();
            return centAmount.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "0";
    }

    /**
     * 元转分，用,分隔
     * @param amount
     * @return
     */
    public static String yuanToCentWithSeparator(String amount) {
        String centAmount = "";
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        try {
            Number number = numberFormat.parse(amount);
            double temp = number.doubleValue() * 100;
            numberFormat.setMaximumFractionDigits(0);
            centAmount = numberFormat.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return centAmount;
    }

    public static void main(String[] args) {
        int centAmount = 101234;
        String amount = "1012.34";
        System.out.println(yuanToCent(amount));
        System.out.println(yuanToCent(amount, true));
        System.out.println(centToYuan(centAmount));
        System.out.println(centToYuan(centAmount, true));
    }
}
