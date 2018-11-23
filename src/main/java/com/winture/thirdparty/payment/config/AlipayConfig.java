package com.winture.thirdparty.payment.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: swinture
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Configuration
@PropertySource("classpath:pay.properties")
@Data
public class AlipayConfig {
    @Value("${alipay.appid}")
    private String appId;

    @Value("${alipay.publicKey}")
    private String alipayPublicKey;

    @Value("${alipay.appPublicKey}")
    private String appPublicKey;

    @Value("${alipay.appPrivateKey}")
    private String appPrivateKey;

    @Value("${alipay.nofityUrl}")
    private String notifyUrl;

    @Value("${alipay.gatewayUrl}")
    private String alipayGatewayUrl;

    /**
     * 加密方式
     */
    @Getter
    private String encryptionMethod = "RSA2";
    /**
     * 超时时间
     */
    @Getter
    private String timeoutExpress = "30m";
    /**
     * 失败
     */
    @Getter
    private String failedCode = "failed";
    /**
     * 成功
     */
    @Getter
    private String successCode = "success";

    @Getter
    private String format = "json";

    @Getter
    private String charset = "utf-8";

}
