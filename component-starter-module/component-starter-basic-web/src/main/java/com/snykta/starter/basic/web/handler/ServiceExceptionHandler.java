package com.snykta.starter.basic.web.handler;


import com.snykta.starter.tools.constant.ExceptionMessageConstant;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyObjUtil;
import com.snykta.starter.tools.utils.CyStrUtil;
import com.snykta.starter.tools.web.result.ResultCode;
import com.snykta.starter.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Ret<Void> handleServiceException(ServiceException e, HttpServletRequest request, HttpServletResponse response) {
        if (CyStrUtil.isEmpty(e.getMessage())) {
            e.setMessage(ExceptionMessageConstant.ERROR_SERVICE_INSIDE);
        }
        if (CyObjUtil.isNull(e.getCode())) {
            e.setCode(ResultCode.ERROR);
        }
        // 设置状态码 = 500
        response.setStatus(500);
        return Ret.fail(e.getCode(), e.getMessage());
    }


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Ret<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        log.error("全局异常捕获 -> 请求地址：{}，不支持：{}请求", requestURI, e.getMethod());
        // 设置状态码 = 405
        response.setStatus(405);
        return Ret.fail("请求方式不支持");
    }


}
