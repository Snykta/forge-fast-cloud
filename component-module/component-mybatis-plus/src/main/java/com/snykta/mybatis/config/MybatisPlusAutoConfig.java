package com.snykta.mybatis.config;



import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * mybatisPlus配置
 *
 */

@Slf4j
@Configuration
@PropertySource("classpath:config/application-mybatis.properties")
public class MybatisPlusAutoConfig implements DisposableBean {

    public MybatisPlusAutoConfig() {
        log.info("初始化[MybatisPlus]模块...");
    }


    /**
     * 添加插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());// 乐观锁插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));// 分页插件
        return interceptor;
    }


    @Override
    public void destroy() throws Exception {
        log.info("关闭[MybatisPlus]模块...");
    }





}
