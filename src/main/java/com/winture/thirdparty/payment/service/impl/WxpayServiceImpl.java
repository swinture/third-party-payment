package com.winture.thirdparty.payment.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import com.winture.thirdparty.payment.config.WxpayConfig;
import com.winture.thirdparty.payment.model.*;
import com.winture.thirdparty.payment.service.PayService;
import com.winture.thirdparty.payment.utils.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: swinture
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Service("wxpayService")
public class WxpayServiceImpl implements PayService {

    private static final Log logger = LogFactory.getLog(WxpayServiceImpl.class);

    private static Gson gson = new Gson();

    @Autowired
    private WxpayConfig wxpayConfig;

    /**
     * 统一下单
     *      --官方文档：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     * @param totalAmount 订单金额，单位分
     * @param outTradeNo 商户订单号
     * @param payDesc
     * @return
     */
    @Override
    public String unifiedOrder(int totalAmount, String outTradeNo, String payDesc) {
        String orderString = null;
        try {
            WXPay wxPay = new WXPay(wxpayConfig);
            WxpayUnifiedOrderRequest request = new WxpayUnifiedOrderRequest();
            request.setAppid(wxpayConfig.getAppID());
            request.setMch_id(wxpayConfig.getMchID());
            request.setNonce_str(WXPayUtil.generateNonceStr());
            request.setNotify_url(wxpayConfig.getNotifyUrl());
            request.setBody(payDesc);
            request.setSpbill_create_ip("");
            request.setOut_trade_no(outTradeNo);
            request.setTotal_fee(totalAmount + "");
            request.setTrade_type("APP");
            String sign = WXPayUtil.generateSignature(MapUtils.java2Map(request),
                    wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            request.setSign(sign);

            Map<String, String> resultMap = wxPay.unifiedOrder(MapUtils.java2Map(request));
            WxpayUnifiedOrderResponse response = new WxpayUnifiedOrderResponse();
            response = MapUtils.map2Java(response, resultMap);
            String returnCode = response.getReturn_code();
            if (returnCode != null && returnCode.equals("SUCCESS")) {
                String resultCode = response.getResult_code();
                if (resultCode != null && resultCode.equals("SUCCESS")) {
                    // 对微信返回结果再次签名，APP端就不用再签名，直接使用
                    WxpayAppPayParam payParam = new WxpayAppPayParam();
                    payParam.setAppid(wxpayConfig.getAppID());
                    payParam.setPartnerid(wxpayConfig.getMchID());
                    payParam.setNoncestr(WXPayUtil.generateNonceStr());
                    payParam.setTimestamp((int)(System.currentTimeMillis() / 1000) + "");
                    payParam.setPrepayid(response.getPrepay_id());
                    Map<String, String> payMap = MapUtils.java2Map(payParam);
                    payMap.put("package", "Sign=WXPay");
                    String paySign = WXPayUtil.generateSignature(payMap,
                            wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
                    payMap.put("sign", paySign);
                    orderString = gson.toJson(payMap);
                }
            } else {
                logger.error(response.getReturn_msg());
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return orderString;
    }

    /**
     * 订单查询
     *      --官方文档：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String orderQuery(String outTradeNo) {
        String wxpayTradeNo = null;
        try {
            WXPay wxPay = new WXPay(wxpayConfig);
            WxpayOrderQueryRequest request = new WxpayOrderQueryRequest();
            request.setAppid(wxpayConfig.getAppID());
            request.setMch_id(wxpayConfig.getMchID());
            request.setNonce_str(WXPayUtil.generateNonceStr());
            request.setOut_trade_no(outTradeNo);
            String sign = WXPayUtil.generateSignature(MapUtils.java2Map(request),
                    wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            request.setSign(sign);

            WxpayOrderQueryResponse response = new WxpayOrderQueryResponse();
            response = MapUtils.map2Java(response, wxPay.orderQuery(MapUtils.java2Map(request)));
            String returnCode = response.getReturn_code();
            if (returnCode != null && returnCode.equals("SUCCESS")) {
                String resultCode = response.getResult_code();
                if (resultCode != null && resultCode.equals("SUCCESS")) {
                    String tradeState = response.getTrade_state();
                    if (tradeState != null && tradeState.equals("SUCCESS")) {
                        wxpayTradeNo = response.getTransaction_id();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return wxpayTradeNo;
    }

    /**
     * 关闭订单
     *      --官方文档：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_3&index=5
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String closeOrder(String outTradeNo) {
        String resultMsg = null;
        try {
            WXPay wxPay = new WXPay(wxpayConfig);
            WxpayCloseOrderRequest request = new WxpayCloseOrderRequest();
            request.setAppid(wxpayConfig.getAppID());
            request.setMch_id(wxpayConfig.getMchID());
            request.setNonce_str(WXPayUtil.generateNonceStr());
            request.setOut_trade_no(outTradeNo);
            String sign = WXPayUtil.generateSignature(MapUtils.java2Map(request),
                    wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            request.setSign(sign);

            WxpayCloseOrderResponse response = new WxpayCloseOrderResponse();
            response = MapUtils.map2Java(response, wxPay.closeOrder(MapUtils.java2Map(request)));
            String returnCode = response.getReturn_code();
            if (returnCode != null && returnCode.equals("SUCCESS")) {
                resultMsg = returnCode;
            } else {
                logger.error(response.getReturn_msg());
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return resultMsg;
    }

    /**
     * 申请退款
     *      --官方文档：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6
     * @param outTradeNo 商户订单号
     * @param outRefundNo 商户退款订单号
     * @param refundAmount 退款金额,单位分
     * @param refundReason 退款原因
     * @return
     */
    @Override
    public String refund(String outTradeNo, String outRefundNo, int refundAmount, String refundReason) {
        String refundResult = null;
        try {
            WXPay wxPay = new WXPay(wxpayConfig);
            WxpayRefundRequest request = new WxpayRefundRequest();
            request.setAppid(wxpayConfig.getAppID());
            request.setMch_id(wxpayConfig.getMchID());
            request.setNonce_str(WXPayUtil.generateNonceStr());
            request.setOut_trade_no(outTradeNo);
            request.setOut_refund_no(outRefundNo);
            request.setTotal_fee(refundAmount + "");
            request.setRefund_fee(refundAmount + "");
            request.setRefund_desc(refundReason);
            String sign = WXPayUtil.generateSignature(MapUtils.java2Map(request),
                    wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            request.setSign(sign);

            WxpayRefundResponse response = new WxpayRefundResponse();
            response = MapUtils.map2Java(response, wxPay.refund(MapUtils.java2Map(request)));
            String returnCode = response.getReturn_code();
            if (returnCode != null && returnCode.equals("SUCCESS")) {
                refundResult = returnCode;
            } else {
                logger.error(response.getReturn_msg());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return refundResult;
    }

    /**
     * 退款查询
     *      --官方文档：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_5&index=7
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String refundQuery(String outTradeNo, String outRefundNo) {
        String refundQueryResult = null;
        try {
            WXPay wxPay = new WXPay(wxpayConfig);
            WxpayRefundQueryRequest request = new WxpayRefundQueryRequest();
            request.setAppid(wxpayConfig.getAppID());
            request.setMch_id(wxpayConfig.getMchID());
            request.setNonce_str(WXPayUtil.generateNonceStr());
            request.setOut_trade_no(outTradeNo);
            String sign = WXPayUtil.generateSignature(MapUtils.java2Map(request),
                    wxpayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
            request.setSign(sign);

            WxpayRefundQueryResponse response = new WxpayRefundQueryResponse();
            response = MapUtils.map2Java(response, wxPay.refundQuery(MapUtils.java2Map(request)));
            String returnCode = response.getReturn_code();
            if (returnCode != null && returnCode.equals("SUCCESS")) {
                refundQueryResult = returnCode;
            } else {
                logger.error(response.getReturn_msg());
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return refundQueryResult;
    }
}
