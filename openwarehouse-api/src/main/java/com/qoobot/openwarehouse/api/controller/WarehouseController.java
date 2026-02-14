package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.warehouse.entity.Warehouse;
import com.qoobot.openwarehouse.warehouse.service.IWarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 仓库管理控制器
 */
@Slf4j
@Tag(name = "仓库管理", description = "仓库管理相关接口")
@RestController
@RequestMapping("/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final IWarehouseService warehouseService;

    @Operation(summary = "分页查询仓库列表", description = "分页查询仓库列表")
    @GetMapping("/page")
    public Result<Page<Warehouse>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库名称") @RequestParam(required = false) String warehouseName) {

        Page<Warehouse> page = new Page<>(current, size);
        Page<Warehouse> result = warehouseService.lambdaQuery()
                .like(warehouseName != null, Warehouse::getWarehouseName, warehouseName)
                .orderByDesc(Warehouse::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询所有仓库", description = "查询所有仓库列表")
    @GetMapping("/list")
    public Result<java.util.List<Warehouse>> list() {
        return Result.success(warehouseService.lambdaQuery()
                .eq(Warehouse::getStatus, 1)
                .list());
    }

    @Operation(summary = "根据ID查询仓库", description = "根据ID查询仓库详情")
    @GetMapping("/{id}")
    public Result<Warehouse> getById(@Parameter(description = "仓库ID") @PathVariable Long id) {
        Warehouse warehouse = warehouseService.getById(id);
        return warehouse != null ? Result.success(warehouse) : Result.error("仓库不存在");
    }

    @Operation(summary = "创建仓库", description = "创建新仓库")
    @PostMapping
    public Result<Void> create(@RequestBody Warehouse warehouse) {
        boolean success = warehouseService.save(warehouse);
        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "更新仓库", description = "更新仓库信息")
    @PutMapping
    public Result<Void> update(@RequestBody Warehouse warehouse) {
        boolean success = warehouseService.updateById(warehouse);
        return success ? Result.success() : Result.error("更新失败");
    }

    @Operation(summary = "删除仓库", description = "根据ID删除仓库")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "仓库ID") @PathVariable Long id) {
        boolean success = warehouseService.removeById(id);
        return success ? Result.success() : Result.error("删除失败");
    }
}
