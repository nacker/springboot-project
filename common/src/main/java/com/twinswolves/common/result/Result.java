package com.twinswolves.common.result;

import lombok.Data;

/**
 * 全局统一返回结果类，用于封装接口返回的数据、状态码和消息，保证接口返回格式的一致性。
 * @param <T> 泛型类型，代表返回数据的类型。
 */
@Data
public class Result<T> {

    // 返回码，用于标识接口请求的处理结果状态，例如 200 表示成功，400 表示请求参数错误等
    private Integer code;

    // 返回消息，用于对请求处理结果进行简要描述，例如 "操作成功"、"参数不能为空" 等
    private String message;

    // 返回数据，包含具体的业务数据，其类型由泛型 T 决定
    private T data;

    /**
     * 无参构造函数，用于创建一个空的 Result 对象。
     */
    public Result() {
    }

    /**
     * 私有方法，用于构建一个包含数据的 Result 对象。
     * 该方法主要作为内部工具方法，供其他构建方法调用。
     * @param data 需要返回的数据，若为 null 则 Result 对象的 data 字段为空。
     * @param <T> 数据的泛型类型。
     * @return 包含数据的 Result 对象。
     */
    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        // 如果数据不为空，则设置到 Result 对象中
        if (data != null)
            result.setData(data);
        return result;
    }

    /**
     * 构建一个包含数据和状态码信息的 Result 对象。
     * @param body 需要返回的数据。
     * @param resultCodeEnum 结果状态码枚举，包含预定义的返回码和返回消息。
     * @param <T> 数据的泛型类型。
     * @return 包含数据和状态码信息的 Result 对象。
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        // 设置返回码
        result.setCode(resultCodeEnum.getCode());
        // 设置返回消息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 构建一个表示成功且包含数据的 Result 对象。
     * @param data 需要返回的数据。
     * @param <T> 数据的泛型类型。
     * @return 表示成功且包含数据的 Result 对象。
     */
    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 构建一个表示成功但不包含数据的 Result 对象。
     * @param <T> 数据的泛型类型。
     * @return 表示成功但不包含数据的 Result 对象。
     */
    public static <T> Result<T> success() {
        return Result.success(null);
    }

    /**
     * 构建一个表示失败且不包含数据的 Result 对象，使用默认失败状态码。
     * @param <T> 数据的泛型类型。
     * @return 表示失败且不包含数据的 Result 对象。
     */
    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    /**
     * 构建一个表示失败且不包含数据的 Result 对象，自定义返回码和消息。
     * @param code 自定义的返回码。
     * @param message 自定义的返回消息。
     * @param <T> 数据的泛型类型。
     * @return 表示失败且不包含数据的 Result 对象。
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = build(null);
        // 设置自定义返回码
        result.setCode(code);
        // 设置自定义返回消息
        result.setMessage(message);
        return result;
    }
}