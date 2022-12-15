package com.my.framework.customConfig.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 处理@Valid错误信息
    @ExceptionHandler(BindException.class)
    public ErrorMessage handlerValidException(BindException e, NativeWebRequest request) {
        return new ErrorMessage(600, e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).distinct().collect(Collectors.joining(",")), e, request);
    }

    @ExceptionHandler(CustomException.class)
    public ErrorMessage handlerCustomException(CustomException e, NativeWebRequest request) {
        return new ErrorMessage(e.getCode(), e.getMessage(), e, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorMessage processRuntimeException(RuntimeException e, NativeWebRequest request) {
        e.printStackTrace();
        return new ErrorMessage("捕获RuntimeException:" + e.getMessage(), e, request);
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage processException(Exception e, NativeWebRequest request) {
        e.printStackTrace();
        return new ErrorMessage("捕获Exception:" + e.getMessage(), e, request);
    }
}
