package com.snykta.basic.web.config.swagger;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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


/**
 * 自动生成接口文档
 */

@Configuration
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = "app.swagger.enable", havingValue = "true")
@Slf4j
@Import({SwaggerBeanPostProcessor.class})
public class SwaggerAutoConfig {

    /**
     * 配置模块名
     */
    @Value("${spring.application.name}")
    private String groupName;

    /**
     * 扫描包路径
     */
    @Value("${app.swagger.package}")
    private String swaggerBasePackage;


    @Bean
    public Docket createRestUrlApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        log.info("开始创建API文档信息...");
        return new ApiInfoBuilder()
                .title("Fast Cloud API")
                .description("Fast-Start-Cloud 接口文档")
                .version("2.0")
                .build();
    }



}
