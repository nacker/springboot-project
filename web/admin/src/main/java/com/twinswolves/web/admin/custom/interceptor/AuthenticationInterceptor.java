package com.twinswolves.web.admin.custom.interceptor;

import com.twinswolves.common.login.LoginUser;
import com.twinswolves.common.login.LoginUserHolder;
import com.twinswolves.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * 认证拦截器，实现了 HandlerInterceptor 接口，用于拦截请求并进行用户认证操作。
 * 在请求处理前解析 JWT 令牌，将用户信息存入 LoginUserHolder 中，
 * 请求处理完成后清除 LoginUserHolder 中的用户信息。
 */
@Component  // 声明该类为 Spring 组件，由 Spring 容器进行管理
public class AuthenticationInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前进行拦截，执行用户认证逻辑。
     * 从请求头中获取 JWT 令牌，解析令牌并将用户信息存入 LoginUserHolder。
     *
     * @param request 当前的 HTTP 请求对象
     * @param response 当前的 HTTP 响应对象
     * @param handler 处理请求的处理器
     * @return 如果认证成功，返回 true 以继续处理请求；否则返回 false 终止请求处理
     * @throws Exception 处理过程中可能抛出的异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中获取名为 "access-token" 的 JWT 令牌
        String token = request.getHeader("access-token");

        // 调用 JwtUtil 工具类解析 JWT 令牌，获取其中的声明信息
        Claims claims = JwtUtil.parseToken(token);
        // 从声明信息中获取用户 ID
        Long userId = claims.get("userId", Long.class);
        // 从声明信息中获取用户名
        String username = claims.get("username", String.class);
        // 将解析得到的用户信息封装成 LoginUser 对象，并存储到 LoginUserHolder 中
        LoginUserHolder.setLoginUser(new LoginUser(userId, username));

        // 认证成功，允许请求继续处理
        return true;

    }

    /**
     * 在请求处理完成后，无论请求是否成功，都会执行该方法。
     * 用于清除 LoginUserHolder 中存储的用户信息，避免内存泄漏。
     *
     * @param request 当前的 HTTP 请求对象
     * @param response 当前的 HTTP 响应对象
     * @param handler 处理请求的处理器
     * @param ex 处理过程中抛出的异常，如果没有异常则为 null
     * @throws Exception 处理过程中可能抛出的异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除 LoginUserHolder 中存储的用户信息
        LoginUserHolder.clear();
    }
}
