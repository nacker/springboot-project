package com.twinswolves.common.utils;

import com.twinswolves.common.exception.BusinessException;
import com.twinswolves.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JwtUtil 工具类，用于生成和解析 JWT（JSON Web Token）。
 */
public class JwtUtil {

    /**
     * 用于签名和验证 JWT 的密钥。
     * 使用 HMAC-SHA 算法基于指定的字符串生成密钥，该密钥在整个应用生命周期内保持不变，因此使用 final 修饰。
     */
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("CY29Eb04RPNyQPxACH2jBNWFGn0ypMhc".getBytes());

    /**
     * 根据用户 ID 和手机号创建 JWT。
     *
     * @param userId 用户的唯一标识
     * @param mobile 用户的手机号
     * @return 生成的 JWT 字符串
     */
    public static String createToken(Long userId, String mobile) {

        return Jwts.builder()
                // 设置 JWT 的主题
                .setSubject("LOGIN_USER")
                // 设置 JWT 的过期时间，这里设置为 1 年
                .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 24 * 365L))
                // 将用户 ID 作为声明添加到 JWT 中
                .claim("userId", userId)
                // 将用户手机号作为声明添加到 JWT 中
                .claim("mobile", mobile)
                // 使用指定的密钥和签名算法对 JWT 进行签名
                .signWith(secretKey, SignatureAlgorithm.HS256)
                // 生成最终的 JWT 字符串
                .compact();
    }

    /**
     * 解析 JWT 并返回其中的声明信息。
     *
     * @param token 要解析的 JWT 字符串
     * @return JWT 中的声明信息
     * @throws BusinessException 当 token 为空、过期或无效时抛出该异常
     */
    public static Claims parseToken(String token) {

        // 检查 token 是否为空，若为空则抛出业务异常
        if (token == null) {
            throw new BusinessException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try {
            // 使用密钥解析 JWT 并获取声明信息
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            // 若 JWT 已过期，抛出对应的业务异常
            throw new BusinessException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            // 若 JWT 无效，抛出对应的业务异常
            throw new BusinessException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    /**
     * 主方法，用于测试 JWT 生成功能。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 打印生成的 JWT 字符串
        System.out.println(createToken(1L, "13888888888"));
    }
}
