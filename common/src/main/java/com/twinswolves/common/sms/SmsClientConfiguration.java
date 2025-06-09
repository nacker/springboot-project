package com.twinswolves.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信客户端配置类，用于配置阿里云短信服务客户端。
 * 当配置文件中存在 `aliyun.sms.endpoint` 属性时，该配置类生效。
 */
@Configuration
@EnableConfigurationProperties(SmsProperties.class)
@ConditionalOnProperty(name = "aliyun.sms.endpoint")
public class SmsClientConfiguration {

    /**
     * 注入短信服务配置属性对象，用于获取阿里云短信服务的相关配置信息。
     */
    @Autowired
    private SmsProperties properties;

    /**
     * 获取 `SmsProperties` 中保存的 YML 配置属性，利用阿里云 API 构建发送短信的客户端对象。
     *
     * @return 阿里云短信服务客户端对象，用于后续发送短信操作。
     */
    @Bean
    public Client createClient() {
        // 阿里云提供的 API，用于配置请求客户端，创建并返回客户端配置对象
        Config config = new Config();
        // 设置阿里云短信服务的接入点地址
        config.setEndpoint(properties.getEndpoint());
        // 设置阿里云访问密钥 ID
        config.setAccessKeyId(properties.getAccessKeyId());
        // 设置阿里云访问密钥 Secret
        config.setAccessKeySecret(properties.getAccessKeySecret());
        try {
            // 根据配置信息创建阿里云短信服务客户端对象
            return new Client(config);
        } catch (Exception e) {
            // 打印异常堆栈信息，实际生产环境建议使用日志框架记录
            e.printStackTrace();
            // 捕获异常后，将其包装为运行时异常抛出
            throw new RuntimeException(e);
        }
    }
}