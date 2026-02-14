package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.system.dto.UserCreateRequest;
import com.qoobot.openwarehouse.system.dto.UserInfoResponse;
import com.qoobot.openwarehouse.system.dto.UserUpdateRequest;
import com.qoobot.openwarehouse.system.entity.User;
import com.qoobot.openwarehouse.system.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 */
@Slf4j
@Tag(name = "用户管理", description = "用户管理相关接口")
@RestController
@RequestMapping("/system/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "分页查询用户列表", description = "分页查询用户列表")
    @GetMapping("/page")
    public Result<Page<UserInfoResponse>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "用户名") @RequestParam(required = false) String username,
            @Parameter(description = "真实姓名") @RequestParam(required = false) String realName) {

        Page<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(username != null, User::getUsername, username)
               .like(realName != null, User::getRealName, realName)
               .orderByDesc(User::getCreateTime);

        Page<User> userPage = userService.page(page, wrapper);

        // 转换为响应对象
        Page<UserInfoResponse> responsePage = new Page<>();
        BeanUtils.copyProperties(userPage, responsePage);
        responsePage.setRecords(userPage.getRecords().stream().map(user -> {
            UserInfoResponse response = new UserInfoResponse();
            BeanUtils.copyProperties(user, response);
            return response;
        }).toList());

        return Result.success(responsePage);
    }

    @Operation(summary = "根据ID查询用户", description = "根据ID查询用户详情")
    @GetMapping("/{id}")
    public Result<UserInfoResponse> getById(@Parameter(description = "用户ID") @PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }

        UserInfoResponse response = new UserInfoResponse();
        BeanUtils.copyProperties(user, response);

        return Result.success(response);
    }

    @Operation(summary = "创建用户", description = "创建新用户")
    @PostMapping
    public Result<Void> create(@Valid @RequestBody UserCreateRequest request) {
        // 检查用户名是否已存在
        if (userService.getUserByUsername(request.getUsername()) != null) {
            return Result.error("用户名已存在");
        }

        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        boolean success = userService.save(user);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新用户", description = "更新用户信息")
    @PutMapping
    public Result<Void> update(@Valid @RequestBody UserUpdateRequest request) {
        User user = userService.getById(request.getId());
        if (user == null) {
            return Result.error("用户不存在");
        }

        BeanUtils.copyProperties(request, user);
        boolean success = userService.updateById(user);

        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除用户", description = "根据ID删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "用户ID") @PathVariable Long id) {
        boolean success = userService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
