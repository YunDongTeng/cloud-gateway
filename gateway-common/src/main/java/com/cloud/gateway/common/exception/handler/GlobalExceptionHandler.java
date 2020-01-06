package com.cloud.gateway.common.exception.handler;


import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.common.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类(业务异常处理)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public Result handle(Exception e) {
        return new Result().fail(500, e.getMessage());
    }
}
