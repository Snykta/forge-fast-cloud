package com.snykta.basic.web.web.controller;

import com.snykta.tools.web.result.ResultCode;
import com.snykta.tools.web.result.Ret;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletRequest;


/**
 * 重写ErrorController异常
 */
@Slf4j
@Controller
public class GlobalErrorController implements ErrorController {


    @ResponseBody
    @RequestMapping(value = "/error")
    public Ret<Void> errorHand(WebRequest webRequest, HttpServletRequest httpServletRequest) {
        Integer statusCode = (Integer)httpServletRequest.getAttribute("javax.servlet.error.status_code");
        String requestURI = String.valueOf(httpServletRequest.getAttribute("javax.servlet.error.request_uri"));
        log.error("Error路由捕获异常 -> 请求地址：{}", requestURI);

        if (statusCode == 404) {
            return Ret.fail(ResultCode.NOT_FOUND, "请求地址不存在");
        }

        return Ret.fail(ResultCode.ERROR, "系统内部异常");
    }


}
