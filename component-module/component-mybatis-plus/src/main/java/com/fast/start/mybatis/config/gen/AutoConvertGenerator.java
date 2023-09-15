package com.fast.start.mybatis.config.gen;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
@Slf4j
public class AutoConvertGenerator {


    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassWord;


    /**
     * 根据表名生成相应结构代码
     *
     * @param tableName 表名 多个用英文,分割
     */
    public void doGeneration(String tableName) {
        DataSourceConfig.Builder dataSourceConfig =
                new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassWord)
                        .dbQuery(new MySqlQuery())
                        .typeConvert(new GenSqlTypeConvert());

        if (StrUtil.isEmpty(dbUrl)) {
            log.error("数据库连接地址为空！");
            return;
        }

        System.out.println(String.format("当前数据库连接地址为：%s \r\n 是否继续[y/n]？", dbUrl));
        Scanner sc = new Scanner(System.in);
        if (!StrUtil.equals(sc.nextLine(), "y", true)) {
            return;
        }

        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfig);

        fastAutoGenerator.globalConfig(builder -> {
            builder.author("Snykta")
                    //.enableSwagger() // 启用swagger
                    .disableOpenDir() // 禁止打开输出目录
                    .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
        });

        fastAutoGenerator.packageConfig(builder -> {
            builder.entity("entity")//实体类包名
                    .parent("com.fast.start.system") // 父包名。不能为空
                    .controller("controller")// 控制层包名
                    .mapper("dao") // mapper层包名
                    .other("dto") // 生成dto目录 可不用
                    .service("service") // service层包名
                    .serviceImpl("service.impl") // service实现类包名
                    .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 自定义mapper.xml文件输出目录
        });


        fastAutoGenerator.strategyConfig(builder -> {
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
        });


        // 自定义生成Dto
        fastAutoGenerator.injectionConfig(consumer -> {
            Map<String, String> customFile = new HashMap<>();
            // DTO
            customFile.put("Dto.java", "/templates/entityDTO.java.ftl");

            consumer.customFile(customFile);
        }).templateEngine(new EnhanceFreemarkerTemplateEngine());


        fastAutoGenerator.execute();

    }
}

