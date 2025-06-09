package com.twinswolves.common.exception;

import com.twinswolves.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * 业务异常类，继承自 RuntimeException，用于处理业务逻辑中出现的异常情况。
 * 该类允许通过状态码和错误消息或者响应结果枚举对象来创建异常实例。
 */
@Data
public class BusinessException extends RuntimeException {

    // 异常状态码，用于标识不同类型的业务异常
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param code 异常状态码，用于标识不同类型的业务异常
     * @param message 异常错误消息，用于描述异常发生的具体原因
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 根据响应结果枚举对象创建异常对象
     *
     * @param resultCodeEnum 响应结果枚举对象，包含异常状态码和错误消息
     */
    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    /**
     * 重写 toString 方法，返回包含异常状态码和错误消息的字符串
     *
     * @return 包含异常状态码和错误消息的字符串
     */
    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
