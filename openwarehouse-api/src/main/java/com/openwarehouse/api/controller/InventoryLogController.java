package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.InventoryLog;
import com.openwarehouse.inventory.service.InventoryLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 库存变动记录Controller
 */
@Tag(name = "库存变动记录")
@RestController
@RequestMapping("/api/inventory/log")
public class InventoryLogController {

    @Autowired
    private InventoryLogService inventoryLogService;

    /**
     * 分页查询库存变动记录
     */
    @Operation(summary = "分页查询库存变动记录")
    @GetMapping("/page")
    public Result<Page<InventoryLog>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long materialId,
            @RequestParam(required = false) Integer orderType,
            @RequestParam(required = false) Integer changeType,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Page<InventoryLog> page = new Page<>(current, size);
        QueryWrapper<InventoryLog> queryWrapper = new QueryWrapper<>();
        if (warehouseId != null) {
            queryWrapper.eq("warehouse_id", warehouseId);
        }
        if (materialId != null) {
            queryWrapper.eq("material_id", materialId);
        }
        if (orderType != null) {
            queryWrapper.eq("order_type", orderType);
        }
        if (changeType != null) {
            queryWrapper.eq("change_type", changeType);
        }
        if (startDate != null && endDate != null) {
            queryWrapper.between("create_time", startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(inventoryLogService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询库存变动记录
     */
    @Operation(summary = "根据ID查询库存变动记录")
    @GetMapping("/{id}")
    public Result<InventoryLog> getById(@PathVariable Long id) {
        return Result.success(inventoryLogService.getById(id));
    }
}
