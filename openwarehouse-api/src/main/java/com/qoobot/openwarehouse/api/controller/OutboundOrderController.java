package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.inventory.entity.OutboundOrder;
import com.qoobot.openwarehouse.inventory.service.IOutboundOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 出库单管理控制器
 */
@Slf4j
@Tag(name = "出库单管理", description = "出库单管理相关接口")
@RestController
@RequestMapping("/outbound-order")
@RequiredArgsConstructor
public class OutboundOrderController {

    private final IOutboundOrderService outboundOrderService;

    @Operation(summary = "分页查询出库单", description = "分页查询出库单列表")
    @GetMapping("/page")
    public Result<Page<OutboundOrder>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "出库单号") @RequestParam(required = false) String orderNo) {

        Page<OutboundOrder> page = new Page<>(current, size);
        Page<OutboundOrder> result = outboundOrderService.lambdaQuery()
                .eq(warehouseId != null, OutboundOrder::getWarehouseId, warehouseId)
                .like(orderNo != null, OutboundOrder::getOrderNo, orderNo)
                .orderByDesc(OutboundOrder::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "查询出库单详情", description = "根据ID查询出库单详情")
    @GetMapping("/{id}")
    public Result<OutboundOrder> getById(@Parameter(description = "出库单ID") @PathVariable Long id) {
        OutboundOrder order = outboundOrderService.getById(id);
        return order != null ? Result.success(order) : Result.error("出库单不存在");
    }
}
