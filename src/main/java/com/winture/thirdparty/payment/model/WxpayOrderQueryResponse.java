package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * 查询订单响应
 * @Author: swinture
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayOrderQueryResponse extends WxpayResponse {

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 是否关注公众账号
     */
    private String is_subscribe;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 交易状态
     */
    private String trade_state;
    /**
     * 付款银行
     */
    private String bank_type;
    /**
     * 总金额
     */
    private String total_fee;
    /**
     * 货币种类
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
     * 代金券金额
     */
    private String coupon_fee;
    /**
     * 代金券使用数量
     */
    private String coupon_count;
    /**
     * 代金券ID
     */
    private String coupon_id_$n;
    /**
     * 代金券类型
     */
    private String coupon_type_$n;
    /**
     * 单个代金券支付金额
     */
    private String coupon_fee_$n;
    /**
     * 微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 支付完成时间
     */
    private String time_end;
    /**
     * 交易状态描述
     */
    private String trade_state_desc;
}
