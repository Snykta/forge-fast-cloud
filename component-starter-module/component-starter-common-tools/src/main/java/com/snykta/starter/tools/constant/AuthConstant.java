package com.snykta.starter.tools.constant;

public class AuthConstant {

    /**
     * 请求头token的键
     *
     */
    public static final String head_token_key = "access_token";

    /**
     * token对应的用户所有信息对象
     *
     */
    public static final String token_user_info = "token_user_info";

    /**
     * token 有效期（单位：秒） 默认7天
     */
    public static final long token_timeout = 7 * 24 * 60 * 60;

    /**
     * token的活跃期 有效期（单位：秒） 默认5天  token_timeout > token_active_timeout
     */
    public static final long token_active_timeout = 5 * 24 * 60 * 60;

    /**
     * token活跃期是否自动继签active_timeout时间
     *
     * true：当token的活跃期过期后系统自动续签
     * false：不自动续签，由开发者根据实际业务情况自动刷新token
     */
    public static final boolean isAutoRenew_token = false;

    /**
     * 用户密码加密的偏移量，必须是16位字符
     */
    public static final String iv_salt = "2017$_!($/}17Y$@";

    /**
     * 用户密码加密的Key，必须是16位字符
     */
    public static final String key_salt = "2023%{=U@+*A0?/#";

}
