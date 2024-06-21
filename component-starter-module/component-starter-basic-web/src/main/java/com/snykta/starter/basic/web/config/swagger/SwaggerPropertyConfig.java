package com.snykta.starter.basic.web.config.swagger;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger.app.config")
public class SwaggerPropertyConfig {

    /**
     * swagger是否开启的控制属性 true=开启 false=关闭
     */
    private boolean enable = false;

    /**
     * swagger 扫描Controller的路径
     */
    private String swaggerBasePackage = "xxx.xxx";

    /**
     * swagger 项目名称
     */
    private String appName = "xxx";

}
