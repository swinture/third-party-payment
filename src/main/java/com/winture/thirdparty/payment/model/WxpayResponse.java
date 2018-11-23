package com.winture.thirdparty.payment.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: swinture
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Data
public abstract class WxpayResponse extends WxpayCommon implements Serializable {

    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;
    /**
     * 业务结果
     */
    private String result_code;

}
