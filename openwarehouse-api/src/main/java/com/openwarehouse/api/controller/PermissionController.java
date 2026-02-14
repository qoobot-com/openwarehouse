package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.system.entity.Permission;
import com.openwarehouse.system.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限Controller
 */
@Tag(name = "权限管理")
@RestController
@RequestMapping("/api/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 分页查询权限列表
     */
    @Operation(summary = "分页查询权限列表")
    @GetMapping("/page")
    public Result<Page<Permission>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            Permission permission) {
        Page<Permission> page = new Page<>(current, size);
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        if (permission.getPermissionCode() != null) {
            queryWrapper.like("permission_code", permission.getPermissionCode());
        }
        if (permission.getPermissionName() != null) {
            queryWrapper.like("permission_name", permission.getPermissionName());
        }
        if (permission.getPermissionType() != null) {
            queryWrapper.eq("permission_type", permission.getPermissionType());
        }
        if (permission.getStatus() != null) {
            queryWrapper.eq("status", permission.getStatus());
        }
        queryWrapper.orderByAsc("sort");
        return Result.success(permissionService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询权限
     */
    @Operation(summary = "根据ID查询权限")
    @GetMapping("/{id}")
    public Result<Permission> getById(@PathVariable Long id) {
        return Result.success(permissionService.getById(id));
    }

    /**
     * 新增权限
     */
    @Operation(summary = "新增权限")
    @PostMapping
    public Result<String> add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.success("新增成功");
    }

    /**
     * 修改权限
     */
    @Operation(summary = "修改权限")
    @PutMapping
    public Result<String> update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.success("修改成功");
    }

    /**
     * 删除权限
     */
    @Operation(summary = "删除权限")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        permissionService.removeById(id);
        return Result.success("删除成功");
    }
}
