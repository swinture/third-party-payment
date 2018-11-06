package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayRefundResponse extends WxpayResponse {
    /**
     * 业务结果
     */
    private String result_code;
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 商户退款单号
     */
    private String out_refund_no;
    /**
     * 微信退款单号
     */
    private String refund_id;
    /**
     * 退款金额
     */
    private String refund_fee;
    /**
     * 应结退款金额
     */
    private String settlement_refund_fee;
    /**
     * 标价金额
     */
    private String total_fee;
    /**
     * 应结订单金额
     */
    private String settlement_total_fee;
    /**
     * 标价币种
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private String cash_fee;
    /**
     * 现金支付币种
     */
    private String cash_fee_type;
    /**
     * 现金退款金额
     */
    private String cash_refund_fee;
    /**
     * 代金券类型
     */
    private String coupon_type_$n;
    /**
     * 代金券退款总金额
     */
    private String coupon_refund_fee;
    /**
     * 单个代金券退款金额
     */
    private String coupon_refund_fee_$n;
    /**
     * 退款代金券使用数量
     */
    private String coupon_refund_count;
    /**
     * 退款代金券ID
     */
    private String coupon_refund_id_$n;
}
