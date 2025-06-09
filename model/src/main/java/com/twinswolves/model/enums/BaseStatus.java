package com.twinswolves.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * 基础状态枚举类，实现了 BaseEnum 接口，用于表示账号的不同状态。
 */
public enum BaseStatus implements BaseEnum {
    /**
     * 账号正常状态，对应的代码值为 0，名称为 "正常"。
     */
    ENABLE(0, "正常"),
    /**
     * 账号禁用状态，对应的代码值为 1，名称为 "禁用"。
     */
    DISABLE(1, "禁用"),
    /**
     * 账号注销状态，对应的代码值为 2，名称为 "注销"。此处原代码名称有误，已修正为 "注销"。
     */
    DEREGISTER(2, "注销");

    /**
     * 枚举对应的代码值，使用 MyBatis-Plus 的 @EnumValue 注解标记，
     * 用于在数据库中存储该枚举值时使用该代码值，同时使用 Jackson 的 @JsonValue 注解，
     * 用于在 JSON 序列化时使用该代码值。
     */
    @EnumValue
    @JsonValue
    private final Integer code;

    /**
     * 枚举对应的名称，用于展示给用户或在日志中记录。
     */
    private final String name;

    /**
     * 构造函数，用于初始化枚举实例的代码值和名称。
     *
     * @param code 枚举对应的代码值
     * @param name 枚举对应的名称
     */
    BaseStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取枚举对应的代码值。
     *
     * @return 枚举对应的代码值
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 获取枚举对应的名称。
     *
     * @return 枚举对应的名称
     */
    @Override
    public String getName() {
        return this.name;
    }
}
