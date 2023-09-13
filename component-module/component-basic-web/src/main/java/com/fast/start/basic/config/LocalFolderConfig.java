package com.fast.start.basic.config;


import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.Servlet;
import java.io.File;

@Component
@Slf4j
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class LocalFolderConfig implements ApplicationListener<ApplicationStartedEvent> {



    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("开始注册本地日志存储临时文件目录...");
        String locationTemp = null;
        if (FileUtil.isWindows()){
            locationTemp = "./fast-cloud-logs";
        }else{
            locationTemp = "./fast-cloud-logs";
        }
        File tmpFile = new File(locationTemp);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
    }
}
