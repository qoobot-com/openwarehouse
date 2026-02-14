package com.qoobot.openwarehouse.api.controller;

import com.qoobot.openwarehouse.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 */
@Tag(name = "测试接口", description = "系统测试相关接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "健康检查", description = "检查系统是否正常运行")
    @GetMapping("/health")
    public Result<Map<String, Object>> health() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "OK");
        data.put("timestamp", LocalDateTime.now());
        data.put("message", "OpenWarehouse is running");
        return Result.success(data);
    }

    @Operation(summary = "欢迎信息", description = "获取系统欢迎信息")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Welcome to OpenWarehouse System!");
    }
}
