package com.openwarehouse.api.controller;

import com.openwarehouse.common.entity.Result;
import com.openwarehouse.inventory.entity.*;
import com.openwarehouse.inventory.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 报表Controller
 */
@Tag(name = "报表管理")
@RestController
@RequestMapping("/api/inventory/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 库存台账报表
     */
    @Operation(summary = "库存台账报表")
    @GetMapping("/inventory")
    public Result<List<InventoryReport>> inventoryReport(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long materialId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return Result.success(reportService.inventoryReport(warehouseId, materialId, startDate, endDate));
    }

    /**
     * 入库明细报表
     */
    @Operation(summary = "入库明细报表")
    @GetMapping("/inbound")
    public Result<List<InboundOrder>> inboundReport(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return Result.success(reportService.inboundReport(warehouseId, supplierId, startDate, endDate));
    }

    /**
     * 出库明细报表
     */
    @Operation(summary = "出库明细报表")
    @GetMapping("/outbound")
    public Result<List<OutboundOrder>> outboundReport(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return Result.success(reportService.outboundReport(warehouseId, customerId, startDate, endDate));
    }
}
