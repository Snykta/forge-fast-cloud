//package com.fast.start.mybatis.gen;
//
//import cn.hutool.core.io.FileUtil;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.setting.dialect.Props;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//
//@Slf4j
//public class AutoConvertGenerator {
//
//    /**
//     * 根据表名生成相应结构代码
//     *
//     * @history
//     */
//    public static void doGeneration() {
//        Props applicationProps = new Props("application.properties", "UTF-8");
//        // 数据库地址
//        String dbUrl = applicationProps.getStr("spring.datasource.url");
//        String dbUserName = applicationProps.getStr("spring.datasource.username");
//        String dbPassWord = applicationProps.getStr("spring.datasource.password");
//        System.out.println("............根据表结构开始自动生成代码 [controller dto entity dao service]............");
//        if (StrUtil.isEmpty(dbUrl)) {
//            log.error("数据库连接地址为空，生成失败！");
//            return;
//        }
//        System.out.println(String.format("当前数据库连接地址为：%s \n是否继续[y/n]？", dbUrl));
//        Scanner sc = new Scanner(System.in);
//        if (!StrUtil.equals(sc.nextLine(), "y", true)) {
//            log.error("生成程序已终止.");
//            return;
//        }
//
//        System.out.println("请输入要生成的表名称，多个表名称请用英文,分割，输入完毕按回车进入下一步");
//
//        String tableName = sc.nextLine();
//        if (StrUtil.isEmpty(tableName)) {
//            log.error("输入表名称为空，生成程序已终止.");
//            return;
//        }
//
//        System.out.println("请输controller、dao、serviced等包的父包名，输入完毕按回车进入下一步");
//
//        String parentPack = sc.nextLine();
//        if (StrUtil.isEmpty(parentPack)) {
//            log.error("输入父包名为空，生成程序已终止.");
//            return;
//        }
//
//        String codePath = FileUtil.getAbsolutePath(System.getProperty("user.dir") + "/src/main/java");
//        String xmlPath = FileUtil.getAbsolutePath(System.getProperty("user.dir") + "/src/main/resources/mapper");
//
//        System.out.println(String.format("当前要生成的表名为：[%s]\n代码生成目录为：[%s]\nmapperXML生成目录为：[%s]\n父包名为：[%s]", tableName, codePath, xmlPath, parentPack));
//        System.out.println("是否继续[y/n]？");
//        if (!StrUtil.equals(sc.nextLine(), "y", true)) {
//            log.error("生成程序已终止.");
//            return;
//        }
//
//        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassWord)
//                        .dbQuery(new MySqlQuery());
//
//
//        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfig);
//
//        fastAutoGenerator.globalConfig(builder -> {
//            builder.author("Snykta")
//                    .disableOpenDir() // 禁止打开输出目录
//                    .outputDir(codePath); // 指定输出目录
//        });
//
//        fastAutoGenerator.packageConfig(builder -> {
//            builder.entity("entity")//实体类包名
//                    .parent(parentPack) // 父包名，不能为空
//                    .controller("controller")// 控制层包名
//                    .mapper("dao") // mapper层包名
//                    .da("dto") // 生成dto目录 可不用
//                    .service("service") // service层包名
//                    .serviceImpl("service.impl") // service实现类包名
//                    .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)); // 自定义mapper.xml文件输出目录
//        });
//
//
//        fastAutoGenerator.strategyConfig(builder -> {
//            builder.addInclude(tableName)
//                    .entityBuilder()
//                    .enableLombok()
//                    .enableChainModel()
//                    .naming(NamingStrategy.underline_to_camel)// 数据表映射实体命名策略 转驼峰命
//                    .columnNaming(NamingStrategy.underline_to_camel)// 表字段映射实体属性命名规则 转驼峰命
//                    .idType(IdType.AUTO)// 添加全局主键类型
//                    .formatFileName("%s")// 格式化实体名称，%s取消首字母I
//                    .enableTableFieldAnnotation()// 开启注解
//
//                    // Controller配置
//                    .controllerBuilder()
//                    .superClass("com.fast.start.basic.web.controller.BaseController") // 设置Controller父类
//
//                    // Mapper配置
//                    .mapperBuilder()
//                    .enableMapperAnnotation()//开启mapper注解
//                    .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
//                    .enableBaseColumnList()//启用xml文件中的BaseColumnList
//                    .formatMapperFileName("%sMapper")//格式化Dao类名称
//                    .formatXmlFileName("%sMapper")//格式化xml文件名称
//
//                    // Service配置
//                    .serviceBuilder()
//                    //.formatServiceFileName("%sService")//格式化 service 接口文件名称
//                    .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
//                    .controllerBuilder()
//                    .s()
//                    .enableRestStyle();
//        });
//
//        fastAutoGenerator.
//        // 自定义生成Dto
//        fastAutoGenerator.injectionConfig(consumer -> {
//            Map<String, String> customFile = new HashMap<>();
//            // DTO
//            customFile.put("Dto.java", "/templates/entityDTO.java.ftl");
//
//            consumer.customFile(customFile);
//        }).templateEngine(new EnhanceFreemarkerTemplateEngine());
//
//
//        fastAutoGenerator.execute();
//
//    }
//}
//
