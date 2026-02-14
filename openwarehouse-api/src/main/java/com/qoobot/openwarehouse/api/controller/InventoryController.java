package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.inventory.entity.Inventory;
import com.qoobot.openwarehouse.inventory.service.IInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 库存管理控制器
 */
@Slf4j
@Tag(name = "库存管理", description = "库存管理相关接口")
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @Operation(summary = "分页查询库存", description = "分页查询库存列表")
    @GetMapping("/page")
    public Result<Page<Inventory>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "物料ID") @RequestParam(required = false) Long materialId) {

        Page<Inventory> page = new Page<>(current, size);
        Page<Inventory> result = inventoryService.lambdaQuery()
                .eq(warehouseId != null, Inventory::getWarehouseId, warehouseId)
                .eq(materialId != null, Inventory::getMaterialId, materialId)
                .orderByDesc(Inventory::getUpdateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询库存详情", description = "根据ID查询库存详情")
    @GetMapping("/{id}")
    public Result<Inventory> getById(@Parameter(description = "库存ID") @PathVariable Long id) {
        Inventory inventory = inventoryService.getById(id);
        return inventory != null ? Result.success(inventory) : Result.error("库存不存在");
    }

    @Operation(summary = "更新库存数量", description = "更新库存数量")
    @PutMapping("/{id}/quantity")
    public Result<Void> updateQuantity(
            @Parameter(description = "库存ID") @PathVariable Long id,
            @Parameter(description = "数量") @RequestParam java.math.BigDecimal quantity) {
        boolean success = inventoryService.updateQuantity(id, quantity);
        return success ? Result.success() : Result.error("更新失败");
    }
}
