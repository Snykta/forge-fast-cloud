package com.snykta.starter.basic.cloud.feign.interceptor;

import com.snykta.starter.basic.cloud.feign.decoder.FeignResponseDecoder;
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


    /**
     *
     * 在此可以处理使用Feign调用接口后返回的Response
     * 与如下方法区别在于此拦截器会早于 FeignResponseDecoder 先执行
     * @see FeignResponseDecoder
     *
     */
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        // 原始请求Response
        Response oldResponse = chain.proceed(chain.request());
        // 在此可以进行编辑或者重写Feign的响应结构体(比如重写http状态，永远=200等逻辑)，某些业务场景下具有重大作用，在此作为示例没有进行修改而是原路返回
        return  oldResponse.newBuilder()
                .code(oldResponse.code())
                .message(oldResponse.message())
                .protocol(oldResponse.protocol())
                .body(oldResponse.body())
                .headers(oldResponse.headers())
                .build();
    }
}
