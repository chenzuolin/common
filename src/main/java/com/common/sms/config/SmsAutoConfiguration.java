package com.common.sms.config;

import com.common.sms.config.properties.SmsProperties;
import com.common.sms.core.AliyunSmsTemplate;
import com.common.sms.core.SmsTemplate;
import com.common.sms.core.TencentSmsTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PackageName: com.common.sms.config
 * @ClassName: SmsAutoConfiguration
 * @Description:
 * @Author: czl
 * @Date: 2023/3/16 15:15
 */
@AutoConfiguration
@EnableConfigurationProperties(SmsProperties.class)
public class SmsAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
    @ConditionalOnClass(com.aliyun.dysmsapi20170525.Client.class)
    static class AliyunSmsConfiguration {

        @Bean
        public SmsTemplate aliyunSmsTemplate(SmsProperties smsProperties) {
            return new AliyunSmsTemplate(smsProperties);
        }

    }

    @Configuration
    @ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
    @ConditionalOnClass(com.tencentcloudapi.sms.v20190711.SmsClient.class)
    static class TencentSmsConfiguration {

        @Bean
        public SmsTemplate tencentSmsTemplate(SmsProperties smsProperties) {
            return new TencentSmsTemplate(smsProperties);
        }

    }
}
