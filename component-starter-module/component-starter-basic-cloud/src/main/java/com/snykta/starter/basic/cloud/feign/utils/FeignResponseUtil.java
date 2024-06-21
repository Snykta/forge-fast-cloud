package com.snykta.starter.basic.cloud.feign.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyStrUtil;
import feign.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * feign响应体判断工具类
 */
public class FeignResponseUtil {

    /**
     * 是否启用了GZip压缩
     * true: 是
     * false：否
     * @param response
     * @return
     */
    public static boolean isGZip(Response response) {
        // 获取Content-Encoding类型请求头内容
        List<String> contentHeaders = response.headers().entrySet().parallelStream().filter(obj -> CyStrUtil.equalsIgnoreCase(obj.getKey(), "Content-Encoding")).flatMap(val -> val.getValue().stream()).collect(Collectors.toList());
        return contentHeaders.parallelStream().anyMatch(c -> CyStrUtil.equalsIgnoreCase(c, "gzip"));
    }

    /**
     * 解码响应体的Gzip压缩并返回String
     * @param response
     * @return
     */
    public static String unGZip(Response response) {
        String body = null;
        try {
            byte[] bodyBytes  = IoUtil.readBytes(response.body().asInputStream(), false);
            body = ZipUtil.unGzip(bodyBytes, "utf-8");
        } catch (Exception e) {
            throw new ServiceException("解码响应体GZip异常");
        }
        return body;
    }



}
