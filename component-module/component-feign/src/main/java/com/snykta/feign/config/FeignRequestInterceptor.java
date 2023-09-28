package com.snykta.feign.config;

import cn.hutool.extra.servlet.ServletUtil;
import com.snykta.tools.utils.CyCollectionUtil;
import com.snykta.tools.utils.CyObjUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;



@Component
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
                    if (CyCollectionUtil.isNotEmpty(values)) {
                        template.header(key, values);
                    }
                });
            }
        }
    }



}
