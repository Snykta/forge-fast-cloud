package com.snykta.basic.cloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.annotation.PostConstruct;


@Configuration
@Slf4j
@PropertySource("classpath:config/application-nacos.properties")
@EnableDiscoveryClient
public class NacosAutoConfig implements DisposableBean {

    /**
     * 项目配置模块名
     */
    @Value("${spring.application.name}")
    private String appName;


    @PostConstruct
    public void initialize() {
        log.info(String.format("开始向服务注册中心发布[%s]模块...", appName));
    }




    @Override
    public void destroy() throws Exception {
        log.info(String.format("开始向服务注册中心下线[%s]模块...", appName));
    }


}
