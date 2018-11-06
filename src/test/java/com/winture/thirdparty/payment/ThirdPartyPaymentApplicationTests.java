package com.winture.thirdparty.payment;

import com.winture.thirdparty.payment.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdPartyPaymentApplicationTests {

    @Autowired
    private PayService alipayService;

    @Autowired
    private PayService wxpayService;

    String outTradeNo = "PRI2018110618034067217393";
    String payDesc = "创建订单";

    @Test
    public void createOrder() {
        String alipayOrder = alipayService.unifiedOrder(1, outTradeNo, payDesc);
        System.out.println(alipayOrder);
        String wxpayOrder = wxpayService.unifiedOrder(1, outTradeNo, payDesc);
        System.out.println(wxpayOrder);
    }

    @Test
    public void orderQuery() {
        System.out.println(alipayService.orderQuery(outTradeNo));
        System.out.println(wxpayService.orderQuery(outTradeNo));
    }

}
