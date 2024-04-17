package com.snykta.gateway;

import com.snykta.tools.constant.GeneralConstant;

import java.util.Arrays;
import java.util.List;

/**
 * Gateway项目个性化常量值
 */
public class GatewayConstant extends GeneralConstant {

    /**
     * gateway忽略不校验的URl
     */
    public static final List<String> ignoreUrlList = Arrays.asList("/fast-auth/auth/doLogin",
            "/fast-auth/auth/doRegister", "/fast-auth/auth/doLogout", "/fast-auth/auth/refreshToken");



}
