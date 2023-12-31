package com.snykta.basic.cloud.feign.decoder;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import com.snykta.basic.cloud.feign.utils.FeignResponseUtil;
import com.snykta.tools.exception.ServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;


/**
 * 自定义feign异常结果解码器 Exception.class异常
 */
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {


    @Override
    public ServiceException decode(String methodKey, Response response) {
        ServiceException serviceException = new ServiceException("内部组件服务异常");
        try {
            String body = null;
            if (FeignResponseUtil.isGZip(response)) {
                body = FeignResponseUtil.unGZip(response);
            } else {
                body = IoUtil.read(response.body().asReader(CharsetUtil.CHARSET_UTF_8));
            }
            serviceException = new ServiceException(body);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return serviceException;
    }
}
