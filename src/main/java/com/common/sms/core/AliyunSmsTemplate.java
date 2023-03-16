package com.common.sms.core;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.common.sms.config.properties.SmsProperties;
import com.common.sms.entity.SmsResult;
import com.common.sms.exception.SmsException;
import lombok.SneakyThrows;

import java.util.Map;

/**
 * Aliyun 短信模板
 *
 * @author Lion Li
 * @version 4.2.0
 */
public class AliyunSmsTemplate implements SmsTemplate {

    private SmsProperties properties;

    private Client client;

    @SneakyThrows(Exception.class)
    public AliyunSmsTemplate(SmsProperties smsProperties) {
        this.properties = smsProperties;
        Config config = new Config()
            // 您的AccessKey ID
            .setAccessKeyId(smsProperties.getAccessKeyId())
            // 您的AccessKey Secret
            .setAccessKeySecret(smsProperties.getAccessKeySecret())
            // 访问的域名
            .setEndpoint(smsProperties.getEndpoint());
        this.client = new Client(config);
    }

    @Override
    public SmsResult send(String phones, String templateId, Map<String, String> param) {
        if (StrUtil.isBlank(phones)) {
            throw new SmsException("手机号不能为空");
        }
        if (StrUtil.isBlank(templateId)) {
            throw new SmsException("模板ID不能为空");
        }
        SendSmsRequest req = new SendSmsRequest()
            .setPhoneNumbers(phones)
            .setSignName(properties.getSignName())
            .setTemplateCode(templateId)
            .setTemplateParam(JSON.toJSONString(param));
        try {
            SendSmsResponse resp = client.sendSms(req);
            return SmsResult.builder()
                .isSuccess("OK".equals(resp.getBody().getCode()))
                .message(resp.getBody().getMessage())
                .response(JSON.toJSONString(resp))
                .build();
        } catch (Exception e) {
            throw new SmsException(e.getMessage());
        }
    }

}
