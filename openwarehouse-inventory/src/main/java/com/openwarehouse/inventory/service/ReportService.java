package com.openwarehouse.inventory.service;

import com.openwarehouse.inventory.entity.InventoryReport;
import com.openwarehouse.inventory.entity.InboundOrder;
import com.openwarehouse.inventory.entity.OutboundOrder;

import java.time.LocalDate;
import java.util.List;

/**
 * 报表Service接口
 */
public interface ReportService {

    /**
     * 库存台账报表
     */
    List<InventoryReport> inventoryReport(Long warehouseId, Long materialId, LocalDate startDate, LocalDate endDate);

    /**
     * 入库明细报表
     */
    List<InboundOrder> inboundReport(Long warehouseId, Long supplierId, LocalDate startDate, LocalDate endDate);

    /**
     * 出库明细报表
     */
    List<OutboundOrder> outboundReport(Long warehouseId, Long customerId, LocalDate startDate, LocalDate endDate);
}
