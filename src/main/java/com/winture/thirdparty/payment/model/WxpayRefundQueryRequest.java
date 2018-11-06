package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * 退款查询请求
 * @Author: xy.zheng
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayRefundQueryRequest extends WxpayCommon implements WxpayRequest<WxpayRefundQueryResponse> {
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
     * 偏移量
     */
    private String offset;
}
