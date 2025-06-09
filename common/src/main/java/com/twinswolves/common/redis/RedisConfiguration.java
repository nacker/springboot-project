package com.twinswolves.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 配置类，用于配置 RedisTemplate 实例，方便在项目中使用 Redis 缓存。
 */
@Configuration
public class RedisConfiguration {

    /**
     * 定义一个名为 stringObjectRedisTemplate 的 Bean，用于操作 Redis 数据库。
     * 该 Bean 使用指定的 Redis 连接工厂创建 RedisTemplate 实例，并设置键和值的序列化方式。
     *
     * @param redisConnectionFactory Redis 连接工厂，用于创建 Redis 连接。
     * @return 配置好的 RedisTemplate 实例，键类型为 String，值类型为 Object。
     */
    @Bean
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建一个 RedisTemplate 实例，用于操作 Redis 数据库
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 RedisTemplate 的连接工厂，用于获取 Redis 连接
        template.setConnectionFactory(redisConnectionFactory);
        // 设置键的序列化方式为 String 序列化，方便在 Redis 中以字符串形式存储键
        template.setKeySerializer(RedisSerializer.string());
        // 设置值的序列化方式为 Java 序列化，将 Java 对象序列化为字节数组存储在 Redis 中
        template.setValueSerializer(RedisSerializer.java());

        return template;
    }
}