package com.twinswolves.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * 日志状态枚举类，实现了 BaseEnum 接口，用于表示日志操作的不同状态。
 */
public enum LogEnum implements BaseEnum {
    /**
     * 表示日志操作成功的状态，对应的代码值为 1，名称为 "成功"。
     */
    SUCCESS(1, "成功"),
    /**
     * 表示日志操作失败的状态，对应的代码值为 0，名称为 "失败"。
     */
    ERROR(0, "失败");

    /**
     * 枚举对应的代码值，使用 MyBatis-Plus 的 @EnumValue 注解标记，
     * 用于在数据库中存储该枚举值时使用该代码值，同时使用 Jackson 的 @JsonValue 注解，
     * 用于在 JSON 序列化时使用该代码值。
     */
    @EnumValue
    @JsonValue
    private final Integer code;

    /**
     * 枚举对应的名称，用于直观展示日志操作的状态。
     */
    private final String name;

    /**
     * 构造函数，用于初始化枚举实例的代码值和名称。
     *
     * @param code 枚举对应的代码值
     * @param name 枚举对应的名称
     */
    LogEnum(Integer code, String name) {
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
