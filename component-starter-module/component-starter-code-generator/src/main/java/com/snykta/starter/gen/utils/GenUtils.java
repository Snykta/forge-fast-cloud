package com.snykta.starter.gen.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.IoUtil;
import cn.hutool.setting.dialect.Props;
import com.snykta.starter.gen.dto.ColumnDto;
import com.snykta.starter.gen.dto.TableDto;
import com.snykta.starter.gen.entity.ColumnEntity;
import com.snykta.starter.gen.entity.TableEntity;
import com.snykta.starter.tools.exception.ServiceException;
import com.snykta.starter.tools.utils.CyDateTimeUtil;
import com.snykta.starter.tools.utils.CyDateUtil;
import com.snykta.starter.tools.utils.CyStrUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
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
    public static void generatorCode(TableDto tableDto, List<ColumnDto> columnDtoList, ZipOutputStream zip, String packName) {
        //配置信息
        Props config = getConfig();

        boolean hasBigDecimal = false;

        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableDto.getTableName());
        tableEntity.setComments(tableDto.getTableComment());
        //表名转换成Java类名
        String className = tableToJava(tableDto.getTableName(), config.getStr("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassNameSmall(CyStrUtil.lowerFirst(className));

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
            columnEntity.setAttrNameSmall(CyStrUtil.lowerFirst(attrName));


            // 列的数据类型，转换成Java类型
            String attrType = config.getStr(columnEntity.getDataType(), "无类型");
            columnEntity.setAttrType(attrType);

            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }

            // 是否包含时间类型
            if (CyStrUtil.containsAnyIgnoreCase(attrType, "date", "time")) {
                columnEntity.setHasDateTypeFlag(true);
            } else {
                columnEntity.setHasDateTypeFlag(false);
            }

            // 是否主键
            if (CyStrUtil.equalsIgnoreCase("PRI", column.getColumnKey()) && columnsEntityList.parallelStream().noneMatch(ColumnEntity::getPkFlag)) {
                columnEntity.setPkFlag(true);
            } else {
                columnEntity.setPkFlag(false);
            }

            columnsEntityList.add(columnEntity);
        }
        tableEntity.setColumns(columnsEntityList);

        // 没主键，则第一个字段为主键
        if (columnsEntityList.parallelStream().noneMatch(ColumnEntity::getPkFlag)) {
            columnsEntityList.get(0).setPkFlag(true);
        }



        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class" , "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);



        // 封装模板内部需要引用的数据
        Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("tableName" , tableEntity.getTableName());
        templateMap.put("comments" , tableEntity.getComments());
        templateMap.put("className" , tableEntity.getClassName());
        templateMap.put("classNameSmall" , tableEntity.getClassNameSmall());
        templateMap.put("pathName" , tableEntity.getClassNameSmall().toLowerCase());
        templateMap.put("columns" , tableEntity.getColumns());
        templateMap.put("hasBigDecimal" , hasBigDecimal);
        templateMap.put("package" , packName);
        templateMap.put("author" , config.getStr("author"));
        templateMap.put("date" , CyDateUtil.format(CyDateTimeUtil.now(), DatePattern.NORM_DATE_FORMATTER));



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
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), packName)));
                IoUtil.writeUtf8(zip, false, sw.toString());
                zip.flush();
                IoUtil.close(sw);
                zip.closeEntry();
            } catch (Exception e) {
                throw new ServiceException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
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
        templates.add("template/Mapper.java.vm" );
        templates.add("template/Mapper.xml.vm" );
        templates.add("template/IService.java.vm" );
        templates.add("template/ServiceImpl.java.vm" );
        templates.add("template/Controller.java.vm" );

        return templates;
    }

    /**
     * 列名转换成Java属性名
     */
    private static String columnToJava(String columnName) {
        StringBuilder sb = new StringBuilder();
        CyStrUtil.split(columnName, "_").forEach(key -> {
            sb.append(CyStrUtil.upperFirst(key));
        });
        return sb.toString();
    }

    /**
     * 表名转换成Java类名
     */
    private static String tableToJava(String tableName, String tablePrefix) {
        if (CyStrUtil.isNotBlank(tablePrefix)) {
            tableName = tableName.startsWith(tablePrefix) ? tableName.replaceFirst(tablePrefix, "" ) : tableName;
        }
        StringBuilder sb = new StringBuilder();
        CyStrUtil.split(tableName, "_").forEach(key -> {
            sb.append(CyStrUtil.upperFirst(key));
        });
        return sb.toString();
    }

    /**
     * 获取配置信息
     */
    private static Props getConfig() {
        return new Props("generator.properties");
    }

    /**
     * 获取文件名
     */
    private static String getFileName(String template, String className, String packageName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (CyStrUtil.isNotBlank(packageName)) {
            packagePath += packageName.replace("." , File.separator) + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Mapper.java.vm")) {
            return packagePath + "mapper" + File.separator + className + "Mapper.java";
        }

        if (template.contains("IService.java.vm")) {
            return packagePath + "service" + File.separator + "I" + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm" )) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Mapper.xml.vm" )) {
            return  "main" + File.separator + "resources" + File.separator + "mapper" + File.separator +className + "Mapper.xml";
        }

        if (template.contains("DTO.java.vm" )) {
            return packagePath + "dto" + File.separator + className + "Dto.java";
        }
        throw new ServiceException("渲染模板失败，找不到可匹配的模板");
    }


}
