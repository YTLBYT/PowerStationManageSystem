package com.example.powerstationmanagesystem.Exception;

import com.example.powerstationmanagesystem.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//全局异常处理,该处理能捕获后端出现的未进行异常处理的异常
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    //捕获Service层抛出的异常
    @ExceptionHandler(value = ServiceException.class)
    public Result serviceError(ServiceException e){
        log.error("业务层异常",e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e){
        log.error("系统错误", e);
        return Result.error("系统错误");
    }
}
