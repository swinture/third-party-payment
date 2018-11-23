package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: swinture
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayRefundRequest extends WxpayCommon implements WxpayRequest<WxpayRefundResponse> {
    /**
     * 签名类型
     */
    private String sign_type;
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
     * 订单金额
     */
    private String total_fee;
    /**
     * 退款金额
     */
    private String refund_fee;
    /**
     * 退款货币种类
     */
    private String refund_fee_type;
    /**
     * 退款原因
     */
    private String refund_desc;
    /**
     * 退款资金来源
     */
    private String refund_account;
    /**
     * 退款结果通知url
     */
    private String notify_url;
}
