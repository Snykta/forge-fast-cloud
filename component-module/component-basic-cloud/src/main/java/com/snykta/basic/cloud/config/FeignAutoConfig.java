package com.snykta.basic.cloud.config;



import com.snykta.basic.cloud.decoder.FeignDecoderConfig;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Bean;
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


    /**
     * 自定义feign的解码实现
     * @param messageConverters
     * @param customizers
     * @return
     */
    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters,
                                ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new FeignDecoderConfig(messageConverters, customizers);
    }


    @Override
    public void destroy() throws Exception {
        log.info("关闭[Feign]模块...");
    }
}
