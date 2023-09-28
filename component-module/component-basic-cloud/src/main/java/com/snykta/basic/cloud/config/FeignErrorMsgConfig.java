package com.snykta.basic.cloud.config;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snykta.tools.exception.ServiceException;
import com.snykta.tools.web.result.Ret;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import java.nio.charset.StandardCharsets;

/**
 * 捕获 Feign 服务端异常，包括自定义throw的异常
 */
@Slf4j
@Configuration
public class FeignErrorMsgConfig implements ErrorDecoder {
    @Override
    public ServiceException decode(String methodKey, Response response) {
        ServiceException serviceException = new ServiceException("Feign远端服务请求异常");
        try {
            // 获取原始的返回内容
            String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            Ret ret = JSONUtil.toBean(json, Ret.class);
//            Ret<Object> ret = new ObjectMapper().readValue(json, Ret.class);
            serviceException = new ServiceException(ret);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return serviceException;
    }
}
