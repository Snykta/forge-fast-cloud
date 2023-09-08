package com.fast.start.feign.utils;



import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取FeignBean
 */
@Component("feignContextUtil")
public class FeignContextUtil implements ApplicationContextAware {


    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getFeignBean(String beanName, Class<T> tClass) {
        FeignContext feignContext = applicationContext.getBean("feignContext", FeignContext.class);
        return (T) feignContext.getInstance(beanName, tClass);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FeignContextUtil.applicationContext = applicationContext;
    }
}
