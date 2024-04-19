package com.snykta.eventbus.config;

import com.snykta.eventbus.EventBusTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EventBusAutoConfig  implements DisposableBean {

    @ConditionalOnMissingBean(EventBusTemplate.class)
    @Bean
    public EventBusTemplate eventBusTemplate(ApplicationContext context){
        log.info("初始化[EventBus]模块...");
        return new EventBusTemplate(context);
    }

    @Override
    public void destroy() throws Exception {
        log.info("关闭[EventBus]模块...");
    }
}
