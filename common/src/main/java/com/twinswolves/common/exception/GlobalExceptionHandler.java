package com.twinswolves.common.exception;

import com.twinswolves.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//统一异常处理，全局异常处理器error。
@ControllerAdvice //用于声明处理全局Controller方法异常的类
public class GlobalExceptionHandler {

    /**
     * 全局异常处理方法，捕获并处理所有未被其他异常处理器捕获的异常。
     *
     * @param e 捕获到的异常对象，包含异常的详细信息。
     * @return 返回一个统一的结果对象，用于向前端传递异常处理结果。
     */
    @ExceptionHandler(Exception.class)//用于声明处理异常的方法，`value`属性用于声明该方法处理的异常类型
    @ResponseBody //表示将方法的返回值作为HTTP的响应体
    public Result handle(Exception e) {
        // 记录异常信息，建议使用日志框架替代直接打印堆栈信息
        e.printStackTrace();
        // 返回失败结果给前端。
        return Result.fail();//发生异常时的返回值。
    }

    /**
     * 处理业务异常的方法，当业务逻辑执行过程中抛出 BusinessException 时会调用此方法。
     *
     * @param e 捕获到的业务异常对象，包含异常的状态码和错误消息。
     * @return 返回一个统一的结果对象，携带业务异常的状态码和错误消息给前端。
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        // 记录业务异常信息，建议使用日志框架替代直接打印堆栈信息
        e.printStackTrace();
        // 返回包含业务异常状态码和错误消息的失败结果给前端
        return Result.fail(e.getCode(), e.getMessage());//删除公寓异常时的返回值。
    }
}
