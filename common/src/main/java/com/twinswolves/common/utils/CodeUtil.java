package com.twinswolves.common.utils;

import java.util.Random;

/**
 * 生成随机验证码的工具类。
 * 该类提供了一个静态方法，用于生成指定长度的随机数字验证码。
 */
public class CodeUtil {
    /**
     * 生成指定长度的随机验证码。
     * 验证码由 0 - 9 的随机数字组成。
     *
     * @param length 要生成的验证码的长度，必须为正整数。
     * @return 包含指定长度随机数字的字符串形式的验证码。
     */
    public static String getRandomCode(Integer length) {
        // 使用 StringBuilder 来高效拼接字符
        StringBuilder builder = new StringBuilder();
        // 创建 Random 对象，用于生成随机数
        Random random = new Random();
        // 循环指定次数，每次生成一个随机数字并添加到 StringBuilder 中
        for (int i = 0; i < length; i++) {
            // 生成 0 到 9 之间的随机整数
            int num = random.nextInt(10);
            // 将随机数字添加到 StringBuilder 中
            builder.append(num);
        }
        // 将 StringBuilder 中的内容转换为字符串并返回
        return builder.toString();
    }
}
