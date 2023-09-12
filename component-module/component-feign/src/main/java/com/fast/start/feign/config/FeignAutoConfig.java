package com.fast.start.feign.config;



import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Feign配置
 */
@Configuration
@Slf4j
@EnableFeignClients
public class FeignAutoConfig implements DisposableBean {

    public FeignAutoConfig(){
        log.info("初始化[Feign]模块...");
    }

    /**
     * 设置为 OkHttp访问
     * @return
     */

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    public feign.okhttp.OkHttpClient feignHttpClient(OkHttpClient client) {
        return new feign.okhttp.OkHttpClient(client);
    }


    @Override
    public void destroy() throws Exception {
        log.info("关闭[Feign]模块...");
    }
}
