package com.snykta.basic.cloud.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Feign配置
 */
@Configuration
@Slf4j
@EnableFeignClients(basePackages = {"com.snykta"})
@PropertySource("classpath:config/application-feign.properties")
public class FeignAutoConfig implements DisposableBean {

    public FeignAutoConfig(){
        log.info("初始化[Feign]模块...");
    }



    @Override
    public void destroy() throws Exception {
        log.info("关闭[Feign]模块...");
    }
}
