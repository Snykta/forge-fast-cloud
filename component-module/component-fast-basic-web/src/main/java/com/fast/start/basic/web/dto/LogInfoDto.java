package com.fast.start.basic.web.dto;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import java.io.Serializable;


/**
 * 控制台日志记录输出类
 */
@Data
public class LogInfoDto implements Serializable {

    private String appName;
    private Double totalTime;
    private String className;
    private String methodName;
    private String LogTag = "LogInfoDto";


    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }


}
