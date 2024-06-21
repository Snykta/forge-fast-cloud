package com.snykta.starter.security.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.snykta.starter.tools.constant.ExceptionMessageConstant;
import com.snykta.starter.tools.web.result.ResultCode;
import com.snykta.starter.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;


/**
 * 认证授权全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {


    /**
     * 没登陆
     * @param request
     * @return
     */
    @ExceptionHandler(NotLoginException.class)
    public Ret<Void> notLoginException(HttpServletRequest request) {
        log.error("全局异常捕获 -> 没有登录系统。请求地址：'{}'", request.getRequestURI());
        return Ret.fail(ResultCode.UN_AUTHORIZED, ExceptionMessageConstant.ERROR_UN_AUTHORIZED);
    }


    /**
     * 没权限
     * @param request
     * @return
     */
    @ExceptionHandler(value = {NotRoleException.class, NotPermissionException.class})
    public Ret<Void> notPermissionException(HttpServletRequest request) {
        log.error("全局异常捕获 -> 没有系统权限。请求地址：'{}'", request.getRequestURI());
        return Ret.fail(ResultCode.UN_PERMISSIONS, ExceptionMessageConstant.ERROR_UN_PERMISSIONS);
    }


}
