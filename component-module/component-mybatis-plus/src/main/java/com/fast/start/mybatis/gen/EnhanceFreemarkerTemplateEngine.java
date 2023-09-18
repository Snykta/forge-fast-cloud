package com.fast.start.mybatis.gen;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * 自定义模板
 */
public class EnhanceFreemarkerTemplateEngine  extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.other);
        customFile.forEach((key, value) -> {
            String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
            this.outputFile(new File(fileName), objectMap, value, false);
        });
    }


}
