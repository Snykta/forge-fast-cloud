package com.snykta.basic.cloud.feign.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;


/**
 * OkHttp拦截器
 */
@Slf4j
public class OkHttpInterceptor implements Interceptor {



    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        // 原始请求Response
        Response oldResponse = chain.proceed(chain.request());
        // 在此可以进行编辑或者重写Feign的响应结构体，某些业务场景下具有重大作用，在此作为示例没有进行修改而是原路返回
        return  oldResponse.newBuilder()
                .code(oldResponse.code())
                .message(oldResponse.message())
                .protocol(oldResponse.protocol())
                .body(oldResponse.body())
                .headers(oldResponse.headers())
                .build();
    }
}
