package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * 查询订单请求
 * @Author: xy.zheng
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayOrderQueryRequest extends WxpayCommon implements WxpayRequest<WxpayOrderQueryResponse> {
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
}
