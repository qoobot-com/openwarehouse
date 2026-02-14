package com.qoobot.openwarehouse.api.controller;

import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.system.dto.LoginRequest;
import com.qoobot.openwarehouse.system.dto.LoginResponse;
import com.qoobot.openwarehouse.system.dto.UserInfoResponse;
import com.qoobot.openwarehouse.system.entity.User;
import com.qoobot.openwarehouse.system.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 */
@Slf4j
@Tag(name = "认证管理", description = "用户认证相关接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @Operation(summary = "用户登录", description = "用户使用用户名和密码登录系统")
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("用户登录: {}", request.getUsername());

        // 登录验证
        String token = userService.login(request.getUsername(), request.getPassword());

        // 查询用户信息
        User user = userService.getUserByUsername(request.getUsername());

        // 构造响应
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setRealName(user.getRealName());

        return Result.success(response);
    }

    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的详细信息")
    @GetMapping("/user/info")
    public Result<UserInfoResponse> getUserInfo() {
        // TODO: 从SecurityContext获取当前用户ID
        Long userId = 1L;

        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }

        UserInfoResponse response = new UserInfoResponse();
        BeanUtils.copyProperties(user, response);

        return Result.success(response);
    }

    @Operation(summary = "用户登出", description = "用户退出登录")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        userService.logout(token);
        return Result.success();
    }
}
