package com.snykta.basic.cloud.feign.interceptor;

import cn.hutool.extra.servlet.ServletUtil;
import com.snykta.tools.utils.CyCollectionUtil;
import com.snykta.tools.utils.CyObjUtil;
import com.snykta.tools.utils.CyStrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * feign设置转发请求头
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (CyObjUtil.isNotNull(attributes)) {
            // 转发请求头
            HttpServletRequest request = attributes.getRequest();
            Map<String, List<String>> headersMap = ServletUtil.getHeadersMap(request);
            if (CyCollectionUtil.isNotEmpty(headersMap)) {
                headersMap.forEach((key, values) -> {
                    // Content-Type的信息不转发
                    if (!CyStrUtil.containsIgnoreCase(key, "content-")) {
                        template.header(key, values);
                    }
                });
            }
        }
    }



}
