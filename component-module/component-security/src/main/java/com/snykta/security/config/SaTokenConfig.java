package com.snykta.security.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@PropertySource("classpath:config/application-security.properties")
@Configuration
public class SaTokenConfig {

    public SaTokenConfig() {
        log.info("初始化[Security]模块...");
    }

//
//    @Bean
//    public SaTokenConfig getSaTokenConfigPrimary() {
//        SaTokenConfig config = new SaTokenConfig();
//        config.setTokenName("satoken");             // token 名称（同时也是 cookie 名称）
//        config.setTimeout(30 * 24 * 60 * 60);       // token 有效期（单位：秒），默认30天，-1代表永不过期
//        config.setActiveTimeout(-1);              // token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结
//        config.setIsConcurrent(true);               // 是否允许同一账号多地同时登录（为 true 时允许一起登录，为 false 时新登录挤掉旧登录）
//        config.setIsShare(true);                    // 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token，为 false 时每次登录新建一个 token）
//        config.setTokenStyle("uuid");               // token 风格
//        config.setIsLog(false);                     // 是否输出操作日志
//        return config;
//    }


}
