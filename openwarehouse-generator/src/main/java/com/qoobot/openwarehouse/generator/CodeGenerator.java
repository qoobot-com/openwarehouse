package com.qoobot.openwarehouse.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * MyBatis-Plus 代码生成器
 */
public class CodeGenerator {

    /**
     * 数据库配置
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/openwarehouse?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    /**
     * 生成配置
     */
    private static final String AUTHOR = "OpenWarehouse";
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + "/src/main/java";
    private static final String MAPPER_OUTPUT_DIR = System.getProperty("user.dir") + "/src/main/resources/mapper";
    private static final String PARENT_PACKAGE = "com.qoobot.openwarehouse";

    /**
     * 需要生成的表名
     */
    private static final String[] TABLES = {
        // 系统管理
        "sys_user",
        "sys_role",
        "sys_user_role",
        "sys_menu",
        "sys_role_menu",
        // 认证授权
        "auth_token",
        // 仓储管理
        "wh_warehouse",
        "wh_zone",
        "wh_shelf",
        "wh_location",
        // 货品管理
        "material",
        "material_category",
        // 供应商管理
        "supplier",
        // 库存管理
        "inventory",
        "inbound_order",
        "inbound_order_detail",
        "outbound_order",
        "outbound_order_detail",
        "inventory_log"
    };

    public static void main(String[] args) {
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .outputDir(OUTPUT_DIR)
                            .commentDate("yyyy-MM-dd")
                            .dateType(DateType.TIME_PACK)
                            .enableSwagger();
                })
                .packageConfig(builder -> {
                    builder.parent(PARENT_PACKAGE)
                            .moduleName("")
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_OUTPUT_DIR));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES)
                            .addTablePrefix("sys_", "wh_", "auth_")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .idType(IdType.AUTO)
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("create_by", FieldFill.INSERT))
                            .addTableFills(new Column("update_by", FieldFill.INSERT_UPDATE))
                            .controllerBuilder()
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl");
                })
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
