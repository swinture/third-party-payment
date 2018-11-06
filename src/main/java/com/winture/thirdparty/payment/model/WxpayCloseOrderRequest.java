package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayCloseOrderRequest extends WxpayCommon implements WxpayRequest<WxpayCloseOrderResponse> {
    /**
     * 商户订单号
     */
    private String out_trade_no;
}
