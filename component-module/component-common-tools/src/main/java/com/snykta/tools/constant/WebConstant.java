package com.snykta.tools.constant;

import java.util.Arrays;
import java.util.List;

public class WebConstant {

    /**
     * swagger 扫描路径
     */
    public static final String swaggerBasePackage = "com.snykta";


    /**
     * gateway忽略不校验的URl
     */
    public static final List<String> ignoreUrlList = Arrays.asList("/fast-auth/auth/doLogin",
                    "/fast-auth/auth/doRegister", "/fast-auth/auth/doLogout", "/fast-auth/auth/refreshToken");


    /**
     * 日志全链路记录值的Key
     */
    public static final String logback_trace_uuid = "logback_trace_uuid";
}
