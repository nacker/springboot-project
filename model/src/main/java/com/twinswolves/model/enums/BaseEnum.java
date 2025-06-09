package com.twinswolves.model.enums;

/**
 * 基础枚举接口，定义了枚举类应实现的通用方法。
 * 实现该接口的枚举类需要提供获取代码值和名称的方法，可用于统一管理和处理枚举信息。
 */
public interface BaseEnum {

    /**
     * 获取枚举值对应的代码。
     * 该代码通常是一个整数，用于在系统中唯一标识该枚举值，方便数据存储和传输。
     *
     * @return 枚举值对应的代码，类型为 Integer。
     */
    Integer getCode();

    /**
     * 获取枚举值对应的名称。
     * 该名称通常是一个字符串，用于在界面展示或日志记录中提供更具可读性的信息。
     *
     * @return 枚举值对应的名称，类型为 String。
     */
    String getName();
}