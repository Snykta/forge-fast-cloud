package com.snykta.basic.web.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Servlet;

/**
 * mvc控制器基础配置
 */

@Slf4j
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@PropertySource("classpath:config/application-web.properties")
public class WebMvcConfig implements WebMvcConfigurer {


    public WebMvcConfig() {
        log.info("初始化[WebMvc]模块...");
    }



}
