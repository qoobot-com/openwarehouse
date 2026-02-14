package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Material;
import com.qoobot.openwarehouse.warehouse.service.IMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 物料管理控制器
 */
@Slf4j
@Tag(name = "物料管理", description = "物料管理相关接口")
@RestController
@RequestMapping("/material")
@RequiredArgsConstructor
public class MaterialController {

    private final IMaterialService materialService;

    @Operation(summary = "分页查询物料列表", description = "分页查询物料列表")
    @GetMapping("/page")
    public Result<Page<Material>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "物料名称") @RequestParam(required = false) String materialName,
            @Parameter(description = "物料编码") @RequestParam(required = false) String materialCode,
            @Parameter(description = "分类ID") @RequestParam(required = false) Long categoryId) {

        Page<Material> page = new Page<>(current, size);
        Page<Material> result = materialService.lambdaQuery()
                .like(materialName != null, Material::getMaterialName, materialName)
                .like(materialCode != null, Material::getMaterialCode, materialCode)
                .eq(categoryId != null, Material::getCategoryId, categoryId)
                .orderByDesc(Material::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询所有物料", description = "查询所有物料列表")
    @GetMapping("/list")
    public Result<java.util.List<Material>> list() {
        return Result.success(materialService.lambdaQuery()
                .eq(Material::getStatus, 1)
                .list());
    }

    @Operation(summary = "根据ID查询物料", description = "根据ID查询物料详情")
    @GetMapping("/{id}")
    public Result<Material> getById(@Parameter(description = "物料ID") @PathVariable Long id) {
        Material material = materialService.getById(id);
        return material != null ? Result.success(material) : Result.error("物料不存在");
    }

    @Operation(summary = "创建物料", description = "创建新物料")
    @PostMapping
    public Result<Void> create(@RequestBody Material material) {
        boolean success = materialService.save(material);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新物料", description = "更新物料信息")
    @PutMapping
    public Result<Void> update(@RequestBody Material material) {
        boolean success = materialService.updateById(material);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除物料", description = "根据ID删除物料")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "物料ID") @PathVariable Long id) {
        boolean success = materialService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
