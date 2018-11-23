package com.winture.thirdparty.payment.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.winture.thirdparty.payment.utils.MapUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * APP端支付参数
 * @Author: swinture
 * @Date: 2018/11/6
 * @Version V1.0
 */
@Data
public class WxpayAppPayParam {
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String partnerid;
    /**
     * 预支付交易会话ID
     */
    private String prepayid;
    /**
     * 随机字符串
     */
    private String noncestr;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 签名
     */
    private String sign;

}
