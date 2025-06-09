package com.twinswolves.common.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信服务配置属性类，用于绑定配置文件中以 `aliyun.sms` 为前缀的配置项。
 * 使用 Lombok 的 @Data 注解自动生成 getter、setter、toString 等方法。
 */
@Data
// 从配置文件中读取以 `aliyun.sms` 为前缀的配置项，绑定到该类的属性上
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties {

    /**
     * 阿里云访问密钥 ID，用于身份验证。
     * 从配置文件 `aliyun.sms.access-key-id` 读取对应的值。
     */
    private String accessKeyId;

    /**
     * 阿里云访问密钥 Secret，与 accessKeyId 配合使用进行身份验证。
     * 从配置文件 `aliyun.sms.access-key-secret` 读取对应的值。
     */
    private String accessKeySecret;

    /**
     * 阿里云短信服务的接入点地址。
     * 从配置文件 `aliyun.sms.endpoint` 读取对应的值。
     */
    private String endpoint;
}