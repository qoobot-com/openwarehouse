package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.InventoryAlert;
import com.openwarehouse.inventory.service.InventoryAlertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 库存预警Controller
 */
@Tag(name = "库存预警管理")
@RestController
@RequestMapping("/api/inventory/alert")
public class InventoryAlertController {

    @Autowired
    private InventoryAlertService inventoryAlertService;

    /**
     * 分页查询库存预警列表
     */
    @Operation(summary = "分页查询库存预警列表")
    @GetMapping("/page")
    public Result<Page<InventoryAlert>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            InventoryAlert inventoryAlert) {
        Page<InventoryAlert> page = new Page<>(current, size);
        QueryWrapper<InventoryAlert> queryWrapper = new QueryWrapper<>();
        if (inventoryAlert.getMaterialCode() != null) {
            queryWrapper.like("material_code", inventoryAlert.getMaterialCode());
        }
        if (inventoryAlert.getWarehouseId() != null) {
            queryWrapper.eq("warehouse_id", inventoryAlert.getWarehouseId());
        }
        if (inventoryAlert.getAlertType() != null) {
            queryWrapper.eq("alert_type", inventoryAlert.getAlertType());
        }
        if (inventoryAlert.getAlertStatus() != null) {
            queryWrapper.eq("alert_status", inventoryAlert.getAlertStatus());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(inventoryAlertService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询库存预警
     */
    @Operation(summary = "根据ID查询库存预警")
    @GetMapping("/{id}")
    public Result<InventoryAlert> getById(@PathVariable Long id) {
        return Result.success(inventoryAlertService.getById(id));
    }

    /**
     * 新增库存预警
     */
    @Operation(summary = "新增库存预警")
    @PostMapping
    public Result<String> add(@RequestBody InventoryAlert inventoryAlert) {
        inventoryAlertService.save(inventoryAlert);
        return Result.success("新增成功");
    }

    /**
     * 修改库存预警
     */
    @Operation(summary = "修改库存预警")
    @PutMapping
    public Result<String> update(@RequestBody InventoryAlert inventoryAlert) {
        inventoryAlertService.updateById(inventoryAlert);
        return Result.success("修改成功");
    }

    /**
     * 删除库存预警
     */
    @Operation(summary = "删除库存预警")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        inventoryAlertService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 处理库存预警
     */
    @Operation(summary = "处理库存预警")
    @PutMapping("/handle/{id}")
    public Result<String> handleAlert(@PathVariable Long id, @RequestBody InventoryAlert inventoryAlert) {
        inventoryAlert.setId(id);
        inventoryAlert.setAlertStatus(1);
        inventoryAlertService.updateById(inventoryAlert);
        return Result.success("处理成功");
    }
}
