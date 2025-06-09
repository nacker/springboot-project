package com.twinswolves.common.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 封装登录用户的信息。
 * 该类使用 Lombok 的 @Data 和 @AllArgsConstructor 注解，
 * 自动生成 getter、setter、toString、equals、hashCode 方法以及全参构造函数。
 */
@Data
@AllArgsConstructor
public class LoginUser {

    /**
     * 登录用户的唯一标识。
     * 通常为数据库中用户表的主键，用于唯一确定一个用户。
     */
    private Long userId;

    /**
     * 登录用户的用户名。
     * 是用户在系统中用于登录和识别的名称。
     */
    private String username;
}