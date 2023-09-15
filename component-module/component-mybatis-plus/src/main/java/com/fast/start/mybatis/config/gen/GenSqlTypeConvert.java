package com.fast.start.mybatis.config.gen;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * 自定义类型转换器
 */
public class GenSqlTypeConvert extends MySqlTypeConvert implements ITypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("tinyint(1)")) {
            return DbColumnType.SHORT;
        }
        if (t.contains("date") || t.contains("timestamp") || t.contains("datetime")) {
            return DbColumnType.DATE;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }


}
