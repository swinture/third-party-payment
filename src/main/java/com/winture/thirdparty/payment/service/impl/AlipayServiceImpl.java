package com.winture.thirdparty.payment.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.winture.thirdparty.payment.config.AlipayConfig;
import com.winture.thirdparty.payment.service.PayService;
import com.winture.thirdparty.payment.utils.AmountUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Service("alipayService")
public class AlipayServiceImpl implements PayService {

    private static final Log logger = LogFactory.getLog(AlipayServiceImpl.class);

    private static AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    @PostConstruct
    private void init() {
        if (alipayClient == null) {
            alipayClient = new DefaultAlipayClient(alipayConfig.getAlipayGatewayUrl(),
                    alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                    alipayConfig.getFormat(), alipayConfig.getCharset(),
                    alipayConfig.getAlipayPublicKey(), alipayConfig.getEncryptionMethod());
        }
    }

    /**
     * APP统一下单
     *      --官方文档：https://docs.open.alipay.com/api_1/alipay.trade.app.pay/
     * @param totalAmount 订单金额，单位分
     * @param outTradeNo 商户订单号
     * @param payDesc
     * @return
     */
    @Override
    public String unifiedOrder(int totalAmount, String outTradeNo, String payDesc) {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setTotalAmount(AmountUtils.centToYuan(totalAmount)); //单位为元
        model.setTimeoutExpress(alipayConfig.getTimeoutExpress());
        model.setOutTradeNo(outTradeNo);
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setBody(payDesc);
        model.setSubject(payDesc);
        request.setBizModel(model);

        String orderString = null;
        try {
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if (response.isSuccess()) {
                orderString = response.getBody();
            }
        } catch (AlipayApiException e) {
            logger.error(e);
        }
        return orderString;
    }

    /**
     * 订单查询
     *      --官方文档：https://docs.open.alipay.com/api_1/alipay.trade.query/
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String orderQuery(String outTradeNo) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        request.setBizModel(model);

        String alipayTradeNo = null;
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                String status = response.getTradeStatus();
                if (status != null && status.equals("TRADE_SUCCESS")) {
                    alipayTradeNo = response.getTradeNo();
                }
            }
        } catch (AlipayApiException e) {
            logger.error(e);
        }
        return alipayTradeNo;
    }

    /**
     * 关闭订单
     *      --官方文档：https://docs.open.alipay.com/api_1/alipay.trade.close/
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String closeOrder(String outTradeNo) {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(outTradeNo);
        request.setBizModel(model);

        String resultMsg = null;
        try {
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                resultMsg = response.getMsg();
            } else {
                logger.error(response.getMsg());
            }
        } catch (AlipayApiException e) {
            logger.error(e);
        }
        return resultMsg;
    }

    /**
     * 申请退款
     *      --官方文档：https://docs.open.alipay.com/api_1/alipay.trade.refund/
     * @param outTradeNo 商户订单号
     * @param outRefundNo 商户退款订单号
     * @param refundAmount 退款金额,单位分
     * @param refundReason 退款原因
     * @return
     */
    @Override
    public String refund(String outTradeNo, String outRefundNo, int refundAmount, String refundReason) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(outTradeNo);
        model.setOutRequestNo(outRefundNo);
        model.setRefundAmount(AmountUtils.centToYuan(refundAmount));
        model.setRefundReason(refundReason);
        request.setBizModel(model);

        String refundResult = null;
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                refundResult = response.getMsg();
            } else {
                logger.error(response.getMsg());
            }
        } catch (AlipayApiException e) {
            logger.error(e);
        }
        return refundResult;
    }

    /**
     * 退款查询
     *      --官方文档：https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query/
     * @param outTradeNo 商户订单号
     * @return
     */
    @Override
    public String refundQuery(String outTradeNo, String outRefundNo) {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest ();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setOutRequestNo(outRefundNo);
        request.setBizModel(model);

        String refundQueryResult = null;
        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                refundQueryResult = response.getMsg();
            } else {
                logger.error(response.getMsg());
            }
        } catch (AlipayApiException e) {
            logger.error(e);
        }
        return refundQueryResult;
    }

}
