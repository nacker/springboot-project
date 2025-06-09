package com.twinswolves.web.admin.custom.converter;

import com.twinswolves.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 该组件用于将字符串转换为实现了 BaseEnum 接口的枚举类型。
 * 实现了 Spring 的 ConverterFactory 接口，可将字符串转换为指定的 BaseEnum 子类型。
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    /**
     * 获取一个将字符串转换为指定 BaseEnum 子类型的转换器。
     *
     * @param <T> 目标枚举类型，必须是 BaseEnum 的子类型。
     * @param targetType 目标枚举类型的 Class 对象。
     * @return 一个将字符串转换为指定枚举类型的转换器。
     */
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        // 返回一个匿名转换器，用于将字符串转换为指定的枚举类型
        return new Converter<String, T>() {
            /**
             * 将输入的字符串转换为指定的枚举类型。
             *
             * @param code 输入的字符串，代表枚举的代码值。
             * @return 对应的枚举实例，如果未找到则抛出 IllegalArgumentException。
             */
            @Override
            public T convert(String code) {
                // 获取目标枚举类型的所有枚举常量
                T[] enumConstants = targetType.getEnumConstants();
                // 遍历所有枚举常量
                for (T value : enumConstants) {
                    // 比较枚举常量的代码值与输入的字符串是否相等
                    if (value.getCode().toString().equals(code)) {
                        // 相等则返回该枚举常量
                        return value;
                    }
                }

                // 未找到匹配的枚举常量，抛出异常
                throw new IllegalArgumentException();
            }
        };
    }
}
