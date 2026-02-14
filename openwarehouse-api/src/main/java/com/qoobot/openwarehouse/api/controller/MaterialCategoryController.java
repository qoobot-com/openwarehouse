package com.qoobot.openwarehouse.api.controller;

import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.MaterialCategory;
import com.qoobot.openwarehouse.warehouse.service.IMaterialCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料分类管理控制器
 */
@Slf4j
@Tag(name = "物料分类管理", description = "物料分类管理相关接口")
@RestController
@RequestMapping("/material-category")
@RequiredArgsConstructor
public class MaterialCategoryController {

    private final IMaterialCategoryService categoryService;

    @Operation(summary = "获取分类树", description = "获取完整的物料分类树结构")
    @GetMapping("/tree")
    public Result<List<MaterialCategory>> tree() {
        List<MaterialCategory> categoryTree = categoryService.getCategoryTree();
        return Result.success(categoryTree);
    }

    @Operation(summary = "查询所有分类", description = "查询所有物料分类列表")
    @GetMapping("/list")
    public Result<List<MaterialCategory>> list() {
        return Result.success(categoryService.lambdaQuery()
                .eq(MaterialCategory::getStatus, 1)
                .orderByAsc(MaterialCategory::getSort)
                .list());
    }

    @Operation(summary = "根据ID查询分类", description = "根据ID查询分类详情")
    @GetMapping("/{id}")
    public Result<MaterialCategory> getById(@Parameter(description = "分类ID") @PathVariable Long id) {
        MaterialCategory category = categoryService.getById(id);
        return category != null ? Result.success(category) : Result.error("分类不存在");
    }

    @Operation(summary = "创建分类", description = "创建新分类")
    @PostMapping
    public Result<Void> create(@RequestBody MaterialCategory category) {
        boolean success = categoryService.save(category);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新分类", description = "更新分类信息")
    @PutMapping
    public Result<Void> update(@RequestBody MaterialCategory category) {
        boolean success = categoryService.updateById(category);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除分类", description = "根据ID删除分类")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "分类ID") @PathVariable Long id) {
        boolean success = categoryService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
