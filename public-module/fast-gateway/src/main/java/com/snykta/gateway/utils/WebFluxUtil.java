package com.snykta.gateway.utils;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.snykta.tools.web.result.Ret;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpResponse;

public class WebFluxUtil {


    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param status http状态码
     * @param ret 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, Ret<?> ret) {
        response.setStatusCode(status);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(ret, JSONConfig.create().setIgnoreNullValue(false)).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }


    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param ret 响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Ret<?> ret) {
        response.setStatusCode(HttpStatus.resolve(ret.getCode()));
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(ret, JSONConfig.create().setIgnoreNullValue(false)).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

}
