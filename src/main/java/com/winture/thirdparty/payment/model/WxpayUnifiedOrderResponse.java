package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Data
public class WxpayUnifiedOrderResponse extends WxpayResponse {

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 预支付交易会话标识
     */
    private String prepay_id;
}
