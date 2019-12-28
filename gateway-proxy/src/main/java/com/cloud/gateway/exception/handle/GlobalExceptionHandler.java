package com.cloud.gateway.exception.handle;

import com.cloud.gateway.exception.exception.AppException;
import com.cloud.gateway.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AppException.class)
    public Result handleAppException(AppException e) {
        logger.error("exception: {}", e.getMessage());
        return new Result().fail(500, e.getMessage());
    }

}
