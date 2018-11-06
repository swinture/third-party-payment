package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Data
public class WxpayCommon {
    /**
     * 应用APPID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
}
