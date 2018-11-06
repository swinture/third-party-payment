package com.winture.thirdparty.payment.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;

/**
 * @Author: xy.zheng
 * @Date: 2018/11/5
 * @Version V1.0
 */
@Configuration
@PropertySource("classpath:pay.properties")
public class WxpayConfig extends WXPayConfig {

    @Value("${wxpay.appid}")
    private String appId;

    @Value("${wxpay.mchid}")
    private String mchId;

    @Value("${wxpay.apikey}")
    private String apiKey;

    @Value("${wxpay.appsecret}")
    private String appSecret;

    @Value("${wxpay.notifyUrl}")
    @Getter
    private String notifyUrl;

    @Value("${wxpay.certPath}")
    private String certPath;

    private byte[] certData;

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return apiKey;
    }

    @Override
    public InputStream getCertStream() {
        File certFile = new File(certPath);
        if (!certFile.exists()) {
            return null;
        }
        try (FileInputStream fis = new FileInputStream(certFile)) {
            this.certData = IOUtils.toByteArray(fis);
            ByteArrayInputStream is = new ByteArrayInputStream(certData);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String s, long l, Exception e) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig wxPayConfig) {
                DomainInfo domainInfo = new DomainInfo(WXPayConstants.DOMAIN_API, true);
                return domainInfo;
            }
        };
    }
}
