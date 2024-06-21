package com.snykta.starter.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class EventBusTemplate {

    public ApplicationContext applicationContext;
    public EventBusTemplate(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * 功能:发布事件
     *  使用方式:
     *  监听事件
     *  在方法上增加 EventListener 注解 方法参数与事件的参数类型对应即可
     *  注意：
     *  默认是同步处理如果是异步处理：
     *    - 类加注解 @EnableAsync
     *    - 监听方法上加@Async即可
     * @param event
     */
    public void publish(BaseEvent event) {
        if(this.applicationContext != null) {
            log.debug("发布事件:{}", event.getClass().getSimpleName());
            applicationContext.publishEvent(event);
        }
    }


}
