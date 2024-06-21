package com.snykta.starter.seata.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * Seata分布式事务中心
 *
 * 使用方法：在需要的service方法上添加注解 @GlobalTransactional
 *
 * 需要先搭建 seata-server 服务端
 *
 */
@Slf4j
@Configuration
@PropertySource("classpath:config/application-seata.properties")
public class SeataAutoConfiguration implements DisposableBean {


    public SeataAutoConfiguration() {
        log.info("初始化[Seata]模块...");
    }





    @Override
    public void destroy() throws Exception {
        log.info("关闭[Seata]模块...");
    }

}
