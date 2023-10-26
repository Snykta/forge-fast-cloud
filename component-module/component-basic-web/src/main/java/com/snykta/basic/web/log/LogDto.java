package com.snykta.basic.web.log;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import java.io.Serializable;


/**
 * 控制台日志记录输出类
 */
@Data
public class LogDto implements Serializable {

    /**
     * 模块名称
     */
    private String appName;

    /**
     * 请求耗时
     */
    private Double totalTime;

    /**
     * 类路径
     */
    private String className;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求链路ID
     */
    private String requestLinkId;


    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }


}
