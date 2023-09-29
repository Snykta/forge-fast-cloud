package com.snykta.basic.cloud.feign.decoder;

import cn.hutool.core.util.CharsetUtil;
import com.snykta.basic.cloud.feign.utils.FeignResponseUtil;
import feign.FeignException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 自定义feign响应结果解码器，如果配置了GZip压缩则进行解码
 */
@Slf4j
public class FeignResponseDecoder extends SpringDecoder {


    public FeignResponseDecoder(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        super(messageConverters, customizers);
    }

    /**
     * 重新处理feign的响应结果
     * @param response
     * @param type
     * @return
     * @throws IOException
     * @throws FeignException
     */
    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
       if (FeignResponseUtil.isGZip(response)) {
           // 解码GZip响应结果
           String body = FeignResponseUtil.unGZip(response);
           response = response.toBuilder().body(body, CharsetUtil.CHARSET_UTF_8)
                   .headers(response.headers())
                   .request(response.request())
                   .reason(response.reason())
                   .status(response.status())
                   .reason(response.reason())
                   .build();
       }
       return super.decode(response, type);
    }
}
