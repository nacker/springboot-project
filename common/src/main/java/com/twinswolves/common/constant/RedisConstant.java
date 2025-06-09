package com.twinswolves.common.constant;

/**
 * Redis 常量类，用于存储与 Redis 相关的常量，例如缓存键前缀、过期时间等。
 */
public class RedisConstant {
    /**
     * 管理员登录相关信息在 Redis 中存储的键前缀。
     * 用于构建存储管理员登录信息的 Redis 键，例如存储管理员登录令牌等信息。
     */
    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";

    /**
     * 管理员登录验证码在 Redis 中的过期时间，单位为秒。
     * 表示管理员登录验证码在 Redis 中存储的有效时长，超过该时长验证码将失效。
     */
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;

    /**
     * 应用端登录相关信息在 Redis 中存储的键前缀。
     * 用于构建存储应用端登录信息的 Redis 键，例如存储应用端登录验证码等信息。
     */
    public static final String APP_LOGIN_PREFIX = "app:login:";

    /**
     * 应用端登录验证码重新发送的间隔时间，单位为秒。
     * 表示用户在应用端请求重新发送登录验证码时，需要等待的时间间隔。
     */
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;

    /**
     * 应用端登录验证码在 Redis 中的过期时间，单位为秒。
     * 表示应用端登录验证码在 Redis 中存储的有效时长，超过该时长验证码将失效。
     */
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
}