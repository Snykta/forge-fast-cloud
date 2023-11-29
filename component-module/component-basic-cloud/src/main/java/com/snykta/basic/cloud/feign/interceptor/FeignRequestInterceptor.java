package com.snykta.basic.cloud.feign.interceptor;


import com.snykta.tools.utils.CyCollectionUtil;
import com.snykta.tools.utils.CyStrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.Map;

/**
 * feign设置转发请求头
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headersMap = template.headers();
        if (CyCollectionUtil.isNotEmpty(headersMap)) {
            headersMap.forEach((key, values) -> {
                // Content-Type的信息不转发
                if (CyStrUtil.containsIgnoreCase(key, "content-")) {
                    template.removeHeader(key);
                }
            });
        }
    }



}
