package com.snykta.starter.basic.cloud.feign.config;


import com.snykta.starter.basic.cloud.feign.decoder.FeignErrorDecoder;
import com.snykta.starter.basic.cloud.feign.decoder.FeignResponseDecoder;
import com.snykta.starter.basic.cloud.feign.interceptor.FeignRequestInterceptor;
import com.snykta.starter.basic.cloud.feign.interceptor.OkHttpInterceptor;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;


/**
 * Feign配置
 */
@Configuration
@Slf4j
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
        return new FeignResponseDecoder(messageConverters, customizers);
    }


    /**
     * 设置请求前拦截器
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

    /**
     * 自定义异常解码器
     * @return
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }


    @Bean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }


    /**
     * 创建okHttp实例对象，并且注入okHttp拦截器，feign底层请求使用此对象
     * @return
     */
    @Bean
    public okhttp3.OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new OkHttpInterceptor())
                .build();
    }



    @Override
    public void destroy() throws Exception {
        log.info("关闭[Feign]模块...");
    }
}
