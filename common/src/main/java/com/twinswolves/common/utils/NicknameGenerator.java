package com.twinswolves.common.utils;

import java.security.SecureRandom;

/**
 * 昵称生成工具类，用于生成随机昵称。
 */
public class NicknameGenerator {
    // 昵称的固定前缀，所有生成的昵称都会以该字符串开头
    private static final String PREFIX = "新用户";
    // 用于生成随机字符串的字符集，包含大写字母、小写字母和数字
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    // 随机字符串的长度，生成的昵称中前缀后面跟随的随机字符的数量
    private static final int RANDOM_LENGTH = 6;
    // 安全的随机数生成器，用于从 CHARACTERS 中随机选取字符
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成以 "新用户" 开头，后面跟着 6 位随机字符串的昵称。
     * @return 生成的随机昵称。
     */
    public static String generateNickname() {
        // 使用 StringBuilder 来高效拼接字符串
        StringBuilder sb = new StringBuilder(PREFIX);
        // 循环指定次数，每次生成一个随机字符并添加到 StringBuilder 中
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            // 生成一个 0 到 CHARACTERS 长度之间的随机整数
            int index = RANDOM.nextInt(CHARACTERS.length());
            // 根据随机索引从 CHARACTERS 中获取对应的字符并添加到 StringBuilder 中
            sb.append(CHARACTERS.charAt(index));
        }
        // 将 StringBuilder 中的内容转换为字符串并返回
        return sb.toString();
    }
}