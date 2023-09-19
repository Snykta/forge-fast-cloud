package com.fast.start.basic.handler;


import com.fast.start.basic.exception.ServiceException;
import com.fast.start.basic.utils.FastObjUtil;
import com.fast.start.basic.utils.FastStrUtil;
import com.fast.start.basic.web.utils.ResultCode;
import com.fast.start.basic.web.utils.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Ret<Void> handleServiceException(ServiceException e, HttpServletRequest request)
    {
        if (FastStrUtil.isEmpty(e.getMessage())) {
            e.setMessage("系统内部异常");
        }
        if (FastObjUtil.isNull(e.getCode())) {
            e.setCode(ResultCode.ERROR);
        }
        return Ret.fail(e.getCode(), e.getMessage());
    }


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Ret<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return Ret.fail("请求方式不支持");
    }






}
