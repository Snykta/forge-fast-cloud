package com.fast.start.feign.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Servlet;


/**
 * Feign配置
 */
@Configuration
@Slf4j
@ConditionalOnClass({Servlet.class})
public class FeignAutoConfig implements DisposableBean {

    public FeignAutoConfig(){
        log.info("初始化[Feign]模块");
    }






    @Override
    public void destroy() throws Exception {
        log.info("关闭[Feign]模块");
    }
}
