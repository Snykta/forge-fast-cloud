package com.snykta.starter.gen.config;


import com.snykta.starter.basic.web.config.WebMvcConfig;
import com.snykta.starter.gen.mapper.MySQLGeneratorMapper;
import com.snykta.starter.gen.mapper.OracleGeneratorMapper;
import com.snykta.starter.gen.mapper.PostgreSQLGeneratorMapper;
import com.snykta.starter.gen.mapper.SQLServerGeneratorMapper;
import com.snykta.starter.gen.mapper.base.BaseGeneratorMapper;
import com.snykta.starter.mybatis.plus.config.MybatisPlusAutoConfig;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyStrUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Slf4j
@PropertySource("classpath:application-gen.properties")
@ComponentScan(basePackages = {"com.snykta.starter.gen"})
@MapperScan("com.snykta.starter.gen.mapper")
@ConditionalOnProperty(name = "gen.code.config.enable", havingValue = "true")
@ConditionalOnClass({DataSource.class, MybatisPlusAutoConfig.class, WebMvcConfig.class})
@EnableConfigurationProperties(value = GenPropertyConfig.class)
public class GenConfig {

    @Autowired
    private GenPropertyConfig genPropertyConfig;
    @Resource
    private MySQLGeneratorMapper mySQLGeneratorMapper;
    @Resource
    private OracleGeneratorMapper oracleGeneratorMapper;
    @Resource
    private PostgreSQLGeneratorMapper postgreSQLGeneratorMapper;
    @Resource
    private SQLServerGeneratorMapper sqlServerGeneratorMapper;

    @Bean
    @Primary
    public BaseGeneratorMapper getGeneratorDao() {
        String dbType = genPropertyConfig.getDbType();
        if (CyStrUtil.isEmpty(dbType)) {
            throw new ServiceException("开启自动生成代码功能，需要配置数据库类型");
        }
        if (CyStrUtil.equalsIgnoreCase("mysql", dbType)) {
            return mySQLGeneratorMapper;
        }
        if (CyStrUtil.equalsIgnoreCase("oracle", dbType)) {
            return oracleGeneratorMapper;
        }
        if (CyStrUtil.equalsIgnoreCase("sqlserver", dbType)) {
            return sqlServerGeneratorMapper;
        }
        if (CyStrUtil.equalsIgnoreCase("postgresql", dbType)) {
            return postgreSQLGeneratorMapper;
        }
        throw new ServiceException("不支持当前数据库生成代码：" + dbType);
    }






}
