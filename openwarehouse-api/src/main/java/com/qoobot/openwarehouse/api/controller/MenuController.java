package com.qoobot.openwarehouse.api.controller;

import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.system.entity.Menu;
import com.qoobot.openwarehouse.system.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理控制器
 */
@Slf4j
@Tag(name = "菜单管理", description = "菜单管理相关接口")
@RestController
@RequestMapping("/system/menu")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService menuService;

    @Operation(summary = "获取菜单树", description = "获取完整的菜单树结构")
    @GetMapping("/tree")
    public Result<List<Menu>> tree() {
        List<Menu> menuTree = menuService.getMenuTree();
        return Result.success(menuTree);
    }

    @Operation(summary = "查询所有菜单", description = "查询所有菜单列表")
    @GetMapping("/list")
    public Result<List<Menu>> list() {
        return Result.success(menuService.list());
    }

    @Operation(summary = "根据ID查询菜单", description = "根据ID查询菜单详情")
    @GetMapping("/{id}")
    public Result<Menu> getById(@Parameter(description = "菜单ID") @PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return menu != null ? Result.success(menu) : Result.error("菜单不存在");
    }

    @Operation(summary = "创建菜单", description = "创建新菜单")
    @PostMapping
    public Result<Void> create(@RequestBody Menu menu) {
        boolean success = menuService.save(menu);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新菜单", description = "更新菜单信息")
    @PutMapping
    public Result<Void> update(@RequestBody Menu menu) {
        boolean success = menuService.updateById(menu);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除菜单", description = "根据ID删除菜单")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "菜单ID") @PathVariable Long id) {
        boolean success = menuService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
