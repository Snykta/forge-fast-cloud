package com.fast.start.basic.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.TimeZone;

/**
 * 设置全局服务时区，如果是国外服务则可设置相对应时区
 */

@Slf4j
@Configuration
public class LocalTimeZoneConfig {


    @Bean
    public TimeZone localDebugTimeZone(){
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);
        log.info("设置时区为GMT+8时区...");
        return timeZone;
    }



}
