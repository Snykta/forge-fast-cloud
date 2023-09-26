package com.snykta.tools.constant;

public class AuthConstant {

    /**
     * 请求头token的键
     *
     */
    public static final String head_token_key = "access_token";

    /**
     * token 有效期（单位：秒） 默认7天
     */
    public static final long token_Timeout = 7 * 24 * 60 * 60;

    /**
     * token是否自动继签
     *
     * true：当token过期后系统自动续签
     * false：不自动续签，由开发者根据实际业务情况自动刷新token
     */
    public static final boolean isAutoRenew_token = false;

}
