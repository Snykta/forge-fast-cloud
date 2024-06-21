package com.snykta.starter.basic.web.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "log.record")
public class LogRecordPropertyConfig {

    /**
     * 日志记录类型，仅限非开发环境，开发环境是直接控制台打印
     * 非开发环境日志记录方式（local-本地磁盘方式。cloud-云端收集方式，此方式需要自行搭建云端，例如ES，然后在logback-spring.xml中配置。）
     * 默认：local
     */
    private String type = "local";

}
