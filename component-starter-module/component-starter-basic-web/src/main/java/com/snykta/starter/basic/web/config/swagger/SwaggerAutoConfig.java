package com.snykta.starter.basic.web.config.swagger;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import java.nio.charset.StandardCharsets;


/**
 * 自动生成接口文档
 */

@Configuration
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = "swagger.app.config.enable", havingValue = "true")
@Slf4j
@Import({SwaggerBeanPostProcessor.class})
@EnableConfigurationProperties(value = SwaggerPropertyConfig.class)
public class SwaggerAutoConfig {

    @Autowired
    private SwaggerPropertyConfig swaggerPropertyConfig;

    @Bean
    public Docket createRestUrlApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 分组项目名称
                .groupName(new String(swaggerPropertyConfig.getAppName().getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8))
                .select()
                // 这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerPropertyConfig.getSwaggerBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        log.info("开始创建API文档信息...");
        return new ApiInfoBuilder()
                .title(new String(swaggerPropertyConfig.getAppName().getBytes(StandardCharsets.ISO_8859_1),
                        StandardCharsets.UTF_8))
                .description("Fast-Start-Cloud 接口文档")
                .version("2.0")
                .build();
    }



}
