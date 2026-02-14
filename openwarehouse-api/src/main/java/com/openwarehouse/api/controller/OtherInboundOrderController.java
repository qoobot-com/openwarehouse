package com.openwarehouse.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.OtherInboundOrder;
import com.openwarehouse.inventory.service.OtherInboundOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 其他入库单Controller
 */
@Tag(name = "其他入库单管理")
@RestController
@RequestMapping("/api/inventory/other-inbound")
public class OtherInboundOrderController {

    @Autowired
    private OtherInboundOrderService otherInboundOrderService;

    /**
     * 分页查询其他入库单列表
     */
    @Operation(summary = "分页查询其他入库单列表")
    @GetMapping("/page")
    public Result<Page<OtherInboundOrder>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            OtherInboundOrder otherInboundOrder) {
        Page<OtherInboundOrder> page = new Page<>(current, size);
        QueryWrapper<OtherInboundOrder> queryWrapper = new QueryWrapper<>();
        if (otherInboundOrder.getOrderNo() != null) {
            queryWrapper.like("order_no", otherInboundOrder.getOrderNo());
        }
        if (otherInboundOrder.getType() != null) {
            queryWrapper.eq("type", otherInboundOrder.getType());
        }
        if (otherInboundOrder.getStatus() != null) {
            queryWrapper.eq("status", otherInboundOrder.getStatus());
        }
        queryWrapper.orderByDesc("create_time");
        return Result.success(otherInboundOrderService.page(page, queryWrapper));
    }

    /**
     * 根据ID查询其他入库单
     */
    @Operation(summary = "根据ID查询其他入库单")
    @GetMapping("/{id}")
    public Result<OtherInboundOrder> getById(@PathVariable Long id) {
        return Result.success(otherInboundOrderService.getById(id));
    }

    /**
     * 新增其他入库单
     */
    @Operation(summary = "新增其他入库单")
    @PostMapping
    public Result<String> add(@RequestBody OtherInboundOrder otherInboundOrder) {
        otherInboundOrderService.save(otherInboundOrder);
        return Result.success("新增成功");
    }

    /**
     * 修改其他入库单
     */
    @Operation(summary = "修改其他入库单")
    @PutMapping
    public Result<String> update(@RequestBody OtherInboundOrder otherInboundOrder) {
        otherInboundOrderService.updateById(otherInboundOrder);
        return Result.success("修改成功");
    }

    /**
     * 删除其他入库单
     */
    @Operation(summary = "删除其他入库单")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        otherInboundOrderService.removeById(id);
        return Result.success("删除成功");
    }
}
