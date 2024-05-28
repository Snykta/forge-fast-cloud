package com.snykta.basic.web.web.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.hutool.core.net.NetUtil;


/**
 * logback-spring.xml 获取本机IP
 */
public class IPLogConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return NetUtil.getLocalhostStr();
    }
}
