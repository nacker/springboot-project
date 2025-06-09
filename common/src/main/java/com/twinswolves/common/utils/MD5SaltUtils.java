package com.twinswolves.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * MD5 加盐加密工具类
 * 使用固定盐值增强安全性，将原始密码与固定盐值组合后进行 MD5 哈希处理。
 */
public class MD5SaltUtils {

    // 固定盐值（建议实际项目中改为更复杂的字符串），用于增强密码加密的安全性
    private static final String FIXED_SALT = "MyFixedSalt123!@#";

    /**
     * MD5 加盐加密
     * @param password 原始密码，待加密的字符串
     * @return 加盐后的 MD5 哈希值（32 位小写），加密后的字符串
     */
    public static String encrypt(String password) {
        try {
            // 密码 + 固定盐，将原始密码与固定盐值拼接
            String saltedPassword = password + FIXED_SALT;

            // 获取 MD5 加密实例，用于进行 MD5 哈希计算
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算哈希值，将拼接后的字符串转换为字节数组并进行 MD5 哈希计算
            byte[] hashedBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            // 转换为 16 进制字符串，将字节数组转换为 32 位小写的 16 进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                // 将每个字节转换为 2 位的 16 进制字符串
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            // 若 MD5 算法不可用，抛出运行时异常
            throw new RuntimeException("MD5算法不可用", e);
        }
    }

    /**
     * 验证密码
     * @param inputPassword 用户输入的密码
     * @param storedHash 存储的加盐哈希值
     * @return 是否匹配，若匹配返回 true，否则返回 false
     */
    public static boolean verify(String inputPassword, String storedHash) {
        // 对用户输入的密码进行加密处理
        String hashedInput = encrypt(inputPassword);
        // 比较加密后的输入密码与存储的哈希值是否相等
        return hashedInput.equals(storedHash);
    }

    // 测试
    public static void main(String[] args) {
        String password = "user123";

        // 加密
        String hashedPassword = encrypt(password);
        System.out.println("加盐MD5哈希值: " + hashedPassword);

        // 验证
        boolean isValid = verify("user123", hashedPassword);
        System.out.println("密码验证结果: " + isValid); // true

        boolean isWrong = verify("wrongpass", hashedPassword);
        System.out.println("错误密码验证: " + isWrong); // false
    }
}