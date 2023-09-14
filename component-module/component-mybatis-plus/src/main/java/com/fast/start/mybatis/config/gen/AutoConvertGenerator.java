package com.fast.start.mybatis.config.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class AutoConvertGenerator {




    /**
     * 根据表名生成相应结构代码
     * @param databaseName 数据库名
     * @param tableName 表名
     */
    public void doGeneration(String databaseName,String... tableName){
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/fast_cloud_demo?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai","root","123456")
                .globalConfig(builder -> {
                    builder.author("Snykta")
                            //.enableSwagger() // 启用swagger
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.entity("entity")//实体类包名
                            .parent("com.fast.start.system") // 父包名。不能为空
                            .controller("controller")// 控制层包名
                            .mapper("dao") // mapper层包名
                            .other("dto") // 生成dto目录 可不用
                            .service("service") // service层包名
                            .serviceImpl("service.impl") // service实现类包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+"/src/main/resources/mapper")); // 自定义mapper.xml文件输出目录
                })
                .strategyConfig(builder -> {
                    //设置要生成的表名
                    builder.addInclude(tableName)
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .naming(NamingStrategy.underline_to_camel)// 数据表映射实体命名策略 转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)// 表字段映射实体属性命名规则 转驼峰命
                            .idType(IdType.AUTO)// 添加全局主键类型
                            .formatFileName("%s")// 格式化实体名称，%s取消首字母I
                            .enableTableFieldAnnotation()// 开启注解

                            // Controller配置
                            .controllerBuilder()
                            .superClass("com.fast.start.basic.web.controller.BaseController") // 设置父类

                            // Mapper配置
                            .mapperBuilder()
                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                            .enableBaseColumnList()//启用xml文件中的BaseColumnList
                            .formatMapperFileName("%sMapper")//格式化Dao类名称
                            .formatXmlFileName("%sMapper")//格式化xml文件名称

                            // Service配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .injectionConfig(consumer -> {
            Map<String, String> customFile = new HashMap<>();
            // 配置DTO（需要的话）但是需要有能配置Dto的模板引擎，比如freemarker，但是这里我们用的VelocityEngine，因此不多作介绍
            customFile.put("DTO.java", "/templates/entityDTO.java.ftl");
            consumer.customFile(customFile);
           })
                .execute();
    }




}

