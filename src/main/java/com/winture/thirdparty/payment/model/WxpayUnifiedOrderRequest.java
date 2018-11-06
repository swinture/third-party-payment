package com.winture.thirdparty.payment.model;

import lombok.Data;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Data
public class WxpayUnifiedOrderRequest extends WxpayCommon implements WxpayRequest<WxpayUnifiedOrderResponse> {

    /**
     * 设备号
     */
    private String device_info;
    /**
     * 签名类型
     */
    private String sign_type;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 货币类型
     */
    private String fee_type;
    /**
     * 总金额
     */
    private String total_fee;
    /**
     * 终端IP
     */
    private String spbill_create_ip;
    /**
     * 交易起始时间
     */
    private String time_start;
    /**
     * 交易结束时间
     */
    private String time_expire;
    /**
     * 订单优惠标记
     */
    private String goods_tag;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 指定支付方式
     */
    private String limit_pay;
    /**
     * 场景信息
     */
    private String scene_info;

}
