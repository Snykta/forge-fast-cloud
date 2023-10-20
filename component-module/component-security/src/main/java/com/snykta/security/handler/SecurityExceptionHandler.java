package com.snykta.security.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.snykta.tools.web.result.ResultCode;
import com.snykta.tools.web.result.Ret;
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
        log.error("全局异常捕获-> 未认证，请登录。请求地址：'{}'", request.getRequestURI());
        return Ret.fail(ResultCode.UN_AUTHORIZED, "未认证，请先登录");
    }


    /**
     * 没权限
     * @param request
     * @return
     */
    @ExceptionHandler(value = {NotRoleException.class, NotPermissionException.class})
    public Ret<Void> notPermissionException(HttpServletRequest request) {
        log.error("全局异常捕获-> 权限不足，无法访问。请求地址：'{}'", request.getRequestURI());
        return Ret.fail(ResultCode.UN_PERMISSIONS, "权限不足，无法访问");
    }


}
