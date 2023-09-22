package com.snykta.gen.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.snykta.basic.web.exception.ServiceException;
import com.snykta.basic.web.utils.*;
import com.snykta.gen.dto.ColumnDto;
import com.snykta.gen.dto.TableDto;
import com.snykta.gen.entity.ColumnEntity;
import com.snykta.gen.entity.TableEntity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


import java.io.File;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class GenUtils {



    /**
     * 生成代码
     */
    public static void generatorCode(TableDto tableDto, List<ColumnDto> columnDtoList, ZipOutputStream zip) {
        //配置信息
        Props config = getConfig();

        boolean hasBigDecimal = false;


        TableEntity tableEntity1 = new TableEntity();
        tableEntity1.setTableName(tableDto.getTableName());
        tableEntity1.setComments(tableDto.getTableComment());
        //表名转换成Java类名
        String className = tableToJava(tableDto.getTableName(), config.getStr("tablePrefix"));
        tableEntity1.setClassName(className);
        tableEntity1.setClassNameSmall(FastStrUtil.lowerFirst(className));

        //列信息
        List<ColumnEntity> columnsEntityList = new ArrayList<>();

        for (ColumnDto column : columnDtoList) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.getColumnName());
            columnEntity.setDataType(column.getDataType());
            columnEntity.setComments(column.getColumnComment());
            columnEntity.setExtra(column.getExtra());

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrNameSmall(FastStrUtil.lowerFirst(attrName));


            // 列的数据类型，转换成Java类型
            String attrType = config.getStr(columnEntity.getDataType(), "无类型");
            columnEntity.setAttrType(attrType);

            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }

            // 是否主键
            if (FastStrUtil.equalsIgnoreCase("PRI", column.getColumnKey()) && FastObjUtil.isNull(tableEntity1.getPk())) {
                tableEntity1.setPk(columnEntity);
            }

            columnsEntityList.add(columnEntity);
        }

        tableEntity1.setColumns(columnsEntityList);

        // 没主键，则第一个字段为主键
        if (FastObjUtil.isNull(tableEntity1.getPk())) {
            tableEntity1.setPk(tableEntity1.getColumns().get(0));
        }



        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);



        // 封装模板数据
        Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("tableName" , tableEntity1.getTableName());
        templateMap.put("comments" , tableEntity1.getComments());
        templateMap.put("pk" , tableEntity1.getPk());
        templateMap.put("className" , tableEntity1.getClassName());
        templateMap.put("classNameSmall" , tableEntity1.getClassNameSmall());
        templateMap.put("pathName" , tableEntity1.getClassNameSmall().toLowerCase());
        templateMap.put("columns" , tableEntity1.getColumns());
        templateMap.put("hasBigDecimal" , hasBigDecimal);
        templateMap.put("version" , config.getStr("version"));
        templateMap.put("package" , config.getStr("package"));
        templateMap.put("moduleName" , config.getStr("moduleName"));
        templateMap.put("author" , config.getStr("author"));
        templateMap.put("email" , config.getStr("email"));
//        templateMap.put("datetime" , EyDateUtil.format(EyDateTimeUtil.now(), DatePattern.NORM_DATETIME_FORMAT));
        templateMap.put("date" , EyDateUtil.format(EyDateTimeUtil.now(), DatePattern.NORM_DATE_FORMATTER));




//        for (int i = 0; i <= 10; i++) {
//            templateMap.put("id" + i, EyIdUtil.getSnowflake());
//        }



        VelocityContext context = new VelocityContext(templateMap);



        //获取模板列表
        List<String> templateList = getTemplates();


        for (String template : templateList) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity1.getClassName(), config.getStr("package"), config.getStr("moduleName"))));
                IoUtil.writeUtf8(zip, false, sw.toString());
                zip.flush();
                IoUtil.close(sw);
                zip.closeEntry();
            } catch (Exception e) {
                throw new ServiceException("渲染模板失败，表名：" + tableEntity1.getTableName(), e);
            }
        }

    }


    /**
     * 定义模板数据
     * @return
     */
    private static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("template/DTO.java.vm" );
        templates.add("template/Entity.java.vm" );
        templates.add("template/Dao.java.vm" );
        templates.add("template/Dao.xml.vm" );
        templates.add("template/Service.java.vm" );
        templates.add("template/ServiceImpl.java.vm" );
        templates.add("template/Controller.java.vm" );
        templates.add("template/Excel.java.vm" );

        return templates;
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        StringBuilder sb = new StringBuilder();
        FastStrUtil.split(columnName, "_").forEach(key -> {
            sb.append(StrUtil.upperFirst(key));
        });
        return sb.toString();
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StrUtil.isNotBlank(tablePrefix)) {
            tableName = tableName.startsWith(tablePrefix) ? tableName.replaceFirst(tablePrefix, "" ) : tableName;
        }
        StringBuilder sb = new StringBuilder();
        FastStrUtil.split(tableName, "_").forEach(key -> {
            sb.append(StrUtil.upperFirst(key));
        });
        return sb.toString();
    }

    /**
     * 获取配置信息
     */
    public static Props getConfig() {
        return new Props("generator.properties");
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StrUtil.isNotBlank(packageName)) {
            packagePath += packageName.replace("." , File.separator) + File.separator + "modules" + File.separator + moduleName + File.separator;
        }

        if (template.contains("Entity.java.vm" )) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Excel.java.vm" )) {
            return packagePath + "excel" + File.separator + className + "Excel.java";
        }

        if (template.contains("Dao.java.vm" )) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm" )) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Dao.xml.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
        }

        if (template.contains("DTO.java.vm" )) {
            return packagePath + "dto" + File.separator + className + "DTO.java";
        }
        throw new ServiceException("渲染模板失败，找不到可匹配的模板");
    }


}
