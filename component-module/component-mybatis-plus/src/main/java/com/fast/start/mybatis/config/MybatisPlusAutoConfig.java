package com.fast.start.mybatis.config;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fast.start.mybatis.interceptor.SqlLogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * mybatisPlus配置
 *
 */

@Slf4j
@Configuration
public class MybatisPlusAutoConfig implements DisposableBean {

    public MybatisPlusAutoConfig(){
        log.info("初始化[MybatisPlus]模块...");
    }

    /**
     * 配置模块名
     */
    @Value("${dbType.name:#{null}}")
    private String dbType;


    /**
     * 配置模块名
     */
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 添加插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        if (StrUtil.isEmpty(dbType)) {
            throw new RuntimeException("模块：[" + appName + "] 没有配置数据库类型");
        }
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.getDbType(dbType)));// 分页插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());// 乐观锁插件
        return interceptor;
    }


    @Override
    public void destroy() throws Exception {
        log.info("关闭[MybatisPlus]模块...");
    }
}
