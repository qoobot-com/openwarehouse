package com.qoobot.openwarehouse.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qoobot.openwarehouse.common.Result;
import com.qoobot.openwarehouse.inventory.dto.InboundOrderCreateRequest;
import com.qoobot.openwarehouse.inventory.entity.InboundOrder;
import com.qoobot.openwarehouse.inventory.service.IInboundOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 入库单管理控制器
 */
@Slf4j
@Tag(name = "入库单管理", description = "入库单管理相关接口")
@RestController
@RequestMapping("/inbound-order")
@RequiredArgsConstructor
public class InboundOrderController {

    private final IInboundOrderService inboundOrderService;

    @Operation(summary = "分页查询入库单", description = "分页查询入库单列表")
    @GetMapping("/page")
    public Result<Page<InboundOrder>> page(
            @Parameter(description = "当前页") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "仓库ID") @RequestParam(required = false) Long warehouseId,
            @Parameter(description = "入库单号") @RequestParam(required = false) String orderNo) {

        Page<InboundOrder> page = new Page<>(current, size);
        Page<InboundOrder> result = inboundOrderService.lambdaQuery()
                .eq(warehouseId != null, InboundOrder::getWarehouseId, warehouseId)
                .like(orderNo != null, InboundOrder::getOrderNo, orderNo)
                .orderByDesc(InboundOrder::getCreateTime)
                .page(page);

        return Result.success(result);
    }

    @Operation(summary = "创建入库单", description = "创建新入库单")
    @PostMapping
    public Result<Void> create(@RequestBody InboundOrderCreateRequest request) {
        InboundOrder order = new InboundOrder();
        order.setOrderNo(inboundOrderService.generateOrderNo());
        order.setWarehouseId(request.getWarehouseId());
        order.setOrderType(request.getOrderType());
        order.setSupplierId(request.getSupplierId());
        order.setExpectDate(request.getExpectDate());
        order.setRemark(request.getRemark());
        order.setStatus(0);

        boolean success = inboundOrderService.save(order);
        // TODO: 保存入库单明细
        // TODO: 更新库存

        return success ? Result.success() : Result.error("创建失败");
    }

    @Operation(summary = "查询入库单详情", description = "根据ID查询入库单详情")
    @GetMapping("/{id}")
    public Result<InboundOrder> getById(@Parameter(description = "入库单ID") @PathVariable Long id) {
        InboundOrder order = inboundOrderService.getById(id);
        return order != null ? Result.success(order) : Result.error("入库单不存在");
    }
}
