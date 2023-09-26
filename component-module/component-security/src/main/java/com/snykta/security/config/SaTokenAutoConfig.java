package com.snykta.security.config;


import cn.dev33.satoken.config.SaTokenConfig;
import com.snykta.tools.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@PropertySource("classpath:config/application-security.properties")
@Configuration
public class SaTokenAutoConfig implements DisposableBean {

    public SaTokenAutoConfig() {
        log.info("初始化[Security]模块...");
    }


    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName(AuthConstant.head_token_key);// token 名称（同时也是 cookie 名称）
        config.setTimeout(AuthConstant.token_Timeout); // token 有效期（单位：秒），默认30天，-1代表永不过期
        config.setActiveTimeout(-1); // token 最低活跃频率（单位：秒），如果 token 超过此时间没有访问系统就会被冻结，默认-1 代表不限制，永不冻结
        config.setIsConcurrent(false);  // 是否允许同一账号多地同时登录（为 true 时允许一起登录，为 false 时新登录挤掉旧登录）
        config.setIsShare(true);  // 在多人登录同一账号时，是否共用一个 token （为 true 时所有登录共用一个 token，为 false 时每次登录新建一个 token）
        config.setTokenStyle("tik"); // token 风格
        config.setIsLog(false); // 是否输出操作日志
        config.setMaxTryTimes(5); // 在每次创建 Token 时的最高循环次数，用于保证 Token 唯一性
        config.setIsReadBody(false); // 是否尝试从 请求体 里读取 Token
        config.setIsReadCookie(false); // 是否尝试从 cookie 里读取 Token
        config.setDataRefreshPeriod(60); // 默认数据持久组件实现类中，每次清理过期数据间隔的时间 （单位: 秒）
        config.setAutoRenew(AuthConstant.isAutoRenew_token); // 是否打开自动续签(默认不自动续签)
        config.setIsPrint(false); // 是否在初始化配置时打印版本字符画
        return config;
    }


    @Override
    public void destroy() throws Exception {
        log.info("关闭[Security]模块...");
    }

}
