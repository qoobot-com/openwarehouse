package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.system.entity.Role;
import com.qoobot.openwarehouse.system.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理控制器
 */
@Slf4j
@Tag(name = "角色管理", description = "角色管理相关接口")
@RestController
@RequestMapping("/system/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @Operation(summary = "分页查询角色列表", description = "分页查询角色列表")
    @GetMapping("/page")
    public Result<Page<Role>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "角色名称") @RequestParam(required = false) String roleName) {

        Page<Role> page = new Page<>(current, size);
        Page<Role> result = roleService.lambdaQuery()
                .like(roleName != null, Role::getRoleName, roleName)
                .orderByDesc(Role::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询所有角色", description = "查询所有角色列表")
    @GetMapping("/list")
    public Result<java.util.List<Role>> list() {
        return Result.success(roleService.list());
    }

    @Operation(summary = "根据ID查询角色", description = "根据ID查询角色详情")
    @GetMapping("/{id}")
    public Result<Role> getById(@Parameter(description = "角色ID") @PathVariable Long id) {
        Role role = roleService.getById(id);
        return role != null ? Result.success(role) : Result.error("角色不存在");
    }

    @Operation(summary = "创建角色", description = "创建新角色")
    @PostMapping
    public Result<Void> create(@RequestBody Role role) {
        boolean success = roleService.save(role);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新角色", description = "更新角色信息")
    @PutMapping
    public Result<Void> update(@RequestBody Role role) {
        boolean success = roleService.updateById(role);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除角色", description = "根据ID删除角色")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "角色ID") @PathVariable Long id) {
        boolean success = roleService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
