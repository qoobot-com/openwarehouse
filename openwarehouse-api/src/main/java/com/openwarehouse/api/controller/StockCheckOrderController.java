package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.StockCheckOrder;
import com.openwarehouse.inventory.service.StockCheckOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 盘点单Controller
 */
@Tag(name = "盘点单管理")
@RestController
@RequestMapping("/api/inventory/stock-check")
public class StockCheckOrderController {

    @Autowired
    private StockCheckOrderService stockCheckOrderService;

    /**
     * 分页查询盘点单列表
     */
    @Operation(summary = "分页查询盘点单列表")
    @GetMapping("/page")
    public Result<Page<StockCheckOrder>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            StockCheckOrder stockCheckOrder) {
        Page<StockCheckOrder> page = new Page<>(current, size);
        QueryWrapper<StockCheckOrder> queryWrapper = new QueryWrapper<>();
        if (stockCheckOrder.getCheckNo() != null) {
            queryWrapper.like("check_no", stockCheckOrder.getCheckNo());
        }
        if (stockCheckOrder.getWarehouseId() != null) {
            queryWrapper.eq("warehouse_id", stockCheckOrder.getWarehouseId());
        }
        if (stockCheckOrder.getCheckType() != null) {
            queryWrapper.eq("check_type", stockCheckOrder.getCheckType());
        }
        if (stockCheckOrder.getCheckStatus() != null) {
            queryWrapper.eq("check_status", stockCheckOrder.getCheckStatus());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(stockCheckOrderService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询盘点单
     */
    @Operation(summary = "根据ID查询盘点单")
    @GetMapping("/{id}")
    public Result<StockCheckOrder> getById(@PathVariable Long id) {
        return Result.success(stockCheckOrderService.getById(id));
    }

    /**
     * 新增盘点单
     */
    @Operation(summary = "新增盘点单")
    @PostMapping
    public Result<String> add(@RequestBody StockCheckOrder stockCheckOrder) {
        stockCheckOrderService.save(stockCheckOrder);
        return Result.success("新增成功");
    }

    /**
     * 修改盘点单
     */
    @Operation(summary = "修改盘点单")
    @PutMapping
    public Result<String> update(@RequestBody StockCheckOrder stockCheckOrder) {
        stockCheckOrderService.updateById(stockCheckOrder);
        return Result.success("修改成功");
    }

    /**
     * 删除盘点单
     */
    @Operation(summary = "删除盘点单")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        stockCheckOrderService.removeById(id);
        return Result.success("删除成功");
    }
}
