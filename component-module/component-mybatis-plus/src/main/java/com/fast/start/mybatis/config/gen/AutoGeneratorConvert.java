//package com.fast.start.mybatis.config.gen;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.engine.EnjoyTemplateEngine;
//
//import java.sql.SQLException;
//public class AutoGeneratorConvert extends BaseSQLGenerator {
//
//
//    /**
//     * 数据源配置
//     */
////    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
////            .Builder("jdbc:mysql://xxxx:3306/baomidou?serverTimezone=Asia/Shanghai", "root", "123456")
////            .schema("baomidou")
////            .build();
//
//    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
//            .Builder("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL", "sa", "")
//
//
//    public void testSimple() throws SQLException {
////        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
////        generator.strategy(strategyConfig().build());
////        generator.global(globalConfig().build());
//
//        initDataSource(DATA_SOURCE_CONFIG.build());
//        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
//                // 数据库配置
//                .dataSourceConfig((scanner, builder) -> builder.schema(scanner.apply("请输入表名称")))
//                // 全局配置
//                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称")))
//                // 包配置
//                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名")))
//                // 策略配置
//                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个表名用,隔开")))
//                /*
//                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
//                   .templateEngine(new BeetlTemplateEngine())
//                   .templateEngine(new FreemarkerTemplateEngine())
//                   .templateEngine(new EnjoyTemplateEngine())
//                 */
//                .execute();
//
//    }
//
//
//}
