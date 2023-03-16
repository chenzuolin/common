package com.common.sms.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @PackageName: com.common.sms.config.properties
 * @ClassName: SmsProperties
 * @Description:
 * @Author: czl
 * @Date: 2023/3/16 15:16
 */
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private Boolean enabled;

    /**
     * 配置节点
     * 阿里云 dysmsapi.aliyuncs.com
     * 腾讯云 sms.tencentcloudapi.com
     */
    private String endpoint;

    /**
     * key
     */
    private String accessKeyId;

    /**
     * 密匙
     */
    private String accessKeySecret;

    /*
     * 短信签名
     */
    private String signName;

    /**
     * 短信应用ID (腾讯专属)
     */
    private String sdkAppId;
}
