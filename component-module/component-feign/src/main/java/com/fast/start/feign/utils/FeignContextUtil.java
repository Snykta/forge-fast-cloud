package com.fast.start.feign.utils;


import com.fast.start.basic.utils.SpringContextUtil;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 获取FeignBean
 */
@Component("feignContextUtil")
public class FeignContextUtil {

    public static <T> T getFeignBean(String beanName, Class<T> tClass) {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        FeignContext feignContext = applicationContext.getBean("feignContext", FeignContext.class);
        return (T) feignContext.getInstance(beanName, tClass);
    }

}
