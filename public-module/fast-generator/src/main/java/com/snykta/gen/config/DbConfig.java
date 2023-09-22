package com.snykta.gen.config;

import com.snykta.basic.web.exception.ServiceException;
import com.snykta.basic.web.utils.FastStrUtil;
import com.snykta.gen.mapper.MySQLGeneratorMapper;
import com.snykta.gen.mapper.OracleGeneratorMapper;
import com.snykta.gen.mapper.PostgreSQLGeneratorMapper;
import com.snykta.gen.mapper.SQLServerGeneratorMapper;
import com.snykta.gen.mapper.base.BaseGeneratorMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 *
 * 数据库配置
 *
 */
@Configuration
public class DbConfig {

    @Value("${gen.db.type}")
    private String dbType;

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
        if (FastStrUtil.equalsIgnoreCase("mysql", dbType)) {
            return mySQLGeneratorMapper;
        }
        if (FastStrUtil.equalsIgnoreCase("oracle", dbType)) {
            return oracleGeneratorMapper;
        }
        if (FastStrUtil.equalsIgnoreCase("sqlserver", dbType)) {
            return sqlServerGeneratorMapper;
        }
        if (FastStrUtil.equalsIgnoreCase("postgresql", dbType)) {
            return postgreSQLGeneratorMapper;
        }
        throw new ServiceException("不支持当前数据库生成代码：" + dbType);
    }


}
