package com.example.exception;

import com.example.common.Result;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = "com.example.controller")//controller包抛出的异常都能捕获到,包名要写对
public class GlobalExceptionHandler {//全局异常处理器

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);//打印日志
//捕获全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest request,Exception e){
        log.error("异常信息:",e);
        return Result.error("系统异常");
    }
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result customError(HttpServletRequest request,CustomException e) {
        return Result.error(e.getMsg());
    }

}
