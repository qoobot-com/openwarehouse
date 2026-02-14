package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.OtherOutboundOrder;
import com.openwarehouse.inventory.service.OtherOutboundOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 其他出库单Controller
 */
@Tag(name = "其他出库单管理")
@RestController
@RequestMapping("/api/inventory/other-outbound")
public class OtherOutboundOrderController {

    @Autowired
    private OtherOutboundOrderService otherOutboundOrderService;

    /**
     * 分页查询其他出库单列表
     */
    @Operation(summary = "分页查询其他出库单列表")
    @GetMapping("/page")
    public Result<Page<OtherOutboundOrder>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            OtherOutboundOrder otherOutboundOrder) {
        Page<OtherOutboundOrder> page = new Page<>(current, size);
        QueryWrapper<OtherOutboundOrder> queryWrapper = new QueryWrapper<>();
        if (otherOutboundOrder.getOrderNo() != null) {
            queryWrapper.like("order_no", otherOutboundOrder.getOrderNo());
        }
        if (otherOutboundOrder.getType() != null) {
            queryWrapper.eq("type", otherOutboundOrder.getType());
        }
        if (otherOutboundOrder.getStatus() != null) {
            queryWrapper.eq("status", otherOutboundOrder.getStatus());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(otherOutboundOrderService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询其他出库单
     */
    @Operation(summary = "根据ID查询其他出库单")
    @GetMapping("/{id}")
    public Result<OtherOutboundOrder> getById(@PathVariable Long id) {
        return Result.success(otherOutboundOrderService.getById(id));
    }

    /**
     * 新增其他出库单
     */
    @Operation(summary = "新增其他出库单")
    @PostMapping
    public Result<String> add(@RequestBody OtherOutboundOrder otherOutboundOrder) {
        otherOutboundOrderService.save(otherOutboundOrder);
        return Result.success("新增成功");
    }

    /**
     * 修改其他出库单
     */
    @Operation(summary = "修改其他出库单")
    @PutMapping
    public Result<String> update(@RequestBody OtherOutboundOrder otherOutboundOrder) {
        otherOutboundOrderService.updateById(otherOutboundOrder);
        return Result.success("修改成功");
    }

    /**
     * 删除其他出库单
     */
    @Operation(summary = "删除其他出库单")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        otherOutboundOrderService.removeById(id);
        return Result.success("删除成功");
    }
}
