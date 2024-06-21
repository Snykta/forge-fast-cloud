package com.snykta.starter.basic.cloud.feign.interceptor;



import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * feign设置转发请求头
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        // 在此可以处理使用feign请求前的一些二次逻辑处理

    }



}
