package com.twinswolves.common.login;

/**
 * 工具类，提供保存 loginUser 到 ThreadLocal 的方法。
 * ThreadLocal 用于在每个线程中独立存储数据，确保不同线程间的数据隔离。
 * 该类主要用于在当前线程中存储和获取登录用户信息。
 */
public class LoginUserHolder {

    /**
     * 用于存储登录用户信息的 ThreadLocal 实例。
     * 每个线程都可以独立地往这个 ThreadLocal 中存入和获取自己的登录用户信息。
     */
    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    /**
     * 将登录用户信息存储到当前线程的 ThreadLocal 中。
     *
     * @param loginUser 要存储的登录用户信息对象
     */
    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    /**
     * 从当前线程的 ThreadLocal 中获取登录用户信息。
     *
     * @return 当前线程中存储的登录用户信息对象，如果没有则返回 null
     */
    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    /**
     * 清除当前线程的 ThreadLocal 中存储的登录用户信息。
     * 为避免内存泄漏，建议在使用完 ThreadLocal 后调用此方法。
     */
    public static void clear() {
        threadLocal.remove();
    }
}