package com.snykta.starter.gen.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gen.code.config")
public class GenPropertyConfig {

    /**
     * 是否开启自动生成代码页面 true=开启 false=关闭
     */
    private boolean enable;


    /**
     * 生成代码时指定数据库，可选值有【mysql、oracle、sqlserver、postgresql】
     */
    private String dbType;

}
