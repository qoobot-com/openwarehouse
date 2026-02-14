package com.qoobot.openwarehouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 仓储系统启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.qoobot.openwarehouse")
public class OpenWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenWarehouseApplication.class, args);
        System.out.println("""

                ========================================
                  OpenWarehouse 启动成功!
                  接口文档: http://localhost:8080/swagger-ui.html
                ========================================
                """);
    }
}
