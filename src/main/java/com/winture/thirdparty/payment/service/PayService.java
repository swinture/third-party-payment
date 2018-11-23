package com.winture.thirdparty.payment.service;

/**
 * 支付接口
 * @Author: swinture
 * @Date: 2018/11/5
 * @Version V1.0
 */
public interface PayService {

    /**
     * 统一下单
     * @param totalAmount 订单金额，单位分
     * @param outTradeNo 商户订单号
     * @param payDesc 交易描述
     * @return
     */
    String unifiedOrder(int totalAmount, String outTradeNo, String payDesc);

    /**
     * 订单查询
     * @param outTradeNo 商户订单号
     * @return 第三方支付订单号
     */
    String orderQuery(String outTradeNo);

    /**
     * 关闭订单
     * @param outTradeNo 商户订单号
     * @return
     */
    String closeOrder(String outTradeNo);

    /**
     * 申请退款
     * @param outTradeNo 商户订单号
     * @param outRefundNo 商户退款订单号
     * @param refundAmount 退款金额,单位分
     * @param refundReason 退款原因
     * @return
     */
    String refund(String outTradeNo, String outRefundNo, int refundAmount, String refundReason);

    /**
     * 退款查询
     * @param outTradeNo 商户订单号
     * @param outRefundNo 商户退款订单号
     * @return
     */
    String refundQuery(String outTradeNo, String outRefundNo);
}
