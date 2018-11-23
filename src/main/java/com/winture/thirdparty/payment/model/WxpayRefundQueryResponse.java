package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * 退款查询响应
 * @Author: swinture
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayRefundQueryResponse extends WxpayResponse {
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
     * 订单总退款次数
     */
    private String total_refund_count;
    /**
     * 订单总金额
     */
    private String total_fee;
    /**
     * 订单金额货币种类
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private String cash_fee;
    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;
    /**
     * 应结订单金额
     */
    private String settlement_total_fee;
    /**
     * 退款笔数
     */
    private String refund_count;
    /**
     * 商户退款单号
     */
    private String out_refund_no_$n;
    /**
     * 微信退款单号
     */
    private String refund_id_$n;
    /**
     * 退款渠道
     */
    private String refund_channel_$n;
    /**
     * 退款金额
     */
    private String refund_fee_$n;
    /**
     * 代金券退款金额
     */
    private String coupon_refund_fee_$n;
    /**
     * 代金券使用数量
     */
    private String coupon_refund_count_$n;
    /**
     * 代金券ID
     */
    private String coupon_refund_id_$n_$m;
    /**
     * 代金券类型
     */
    private String coupon_type_$n_$m;
    /**
     * 单个代金券退款金额
     */
    private String coupon_refund_fee_$n_$m;
    /**
     * 退款状态
     */
    private String refund_status_$n;
    /**
     * 退款资金来源
     */
    private String refund_account_$n;
    /**
     * 退款入账账户
     */
    private String refund_recv_accout_$n;
    /**
     * 退款成功时间
     */
    private String refund_success_time_$n;
}
