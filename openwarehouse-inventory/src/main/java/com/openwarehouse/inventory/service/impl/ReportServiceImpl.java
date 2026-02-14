package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.openwarehouse.inventory.entity.*;
import com.openwarehouse.inventory.mapper.*;
import com.openwarehouse.inventory.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 报表Service实现类
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private InboundOrderMapper inboundOrderMapper;

    @Autowired
    private OutboundOrderMapper outboundOrderMapper;

    @Autowired
    private InventoryLogMapper inventoryLogMapper;

    @Override
    public List<InventoryReport> inventoryReport(Long warehouseId, Long materialId,
                                                  LocalDate startDate, LocalDate endDate) {
        List<InventoryReport> reportList = new ArrayList<>();

        // 查询库存信息
        QueryWrapper<Inventory> inventoryWrapper = new QueryWrapper<>();
        if (warehouseId != null) {
            inventoryWrapper.eq("warehouse_id", warehouseId);
        }
        if (materialId != null) {
            inventoryWrapper.eq("material_id", materialId);
        }
        List<Inventory> inventoryList = inventoryMapper.selectList(inventoryWrapper);

        // 查询入库记录
        QueryWrapper<InboundOrderDetail> inboundWrapper = new QueryWrapper<>();
        inboundWrapper.eq("order_type", 1); // 采购入库
        if (warehouseId != null) {
            inboundWrapper.eq("warehouse_id", warehouseId);
        }
        if (startDate != null && endDate != null) {
            inboundWrapper.between("create_time", startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        }
        List<InboundOrderDetail> inboundDetails = inboundOrderDetailMapper.selectList(inboundWrapper);

        // 查询出库记录
        QueryWrapper<OutboundOrderDetail> outboundWrapper = new QueryWrapper<>();
        outboundWrapper.eq("order_type", 1); // 销售出库
        if (warehouseId != null) {
            outboundWrapper.eq("warehouse_id", warehouseId);
        }
        if (startDate != null && endDate != null) {
            outboundWrapper.between("create_time", startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        }
        List<OutboundOrderDetail> outboundDetails = outboundOrderDetailMapper.selectList(outboundWrapper);

        // 组装报表数据
        for (Inventory inventory : inventoryList) {
            InventoryReport report = new InventoryReport();
            report.setWarehouseId(inventory.getWarehouseId());
            report.setMaterialId(inventory.getMaterialId());
            report.setEndQuantity(inventory.getQuantity());
            report.setEndAmount(inventory.getQuantity().multiply(BigDecimal.valueOf(100))); // 假设单价100

            // TODO: 根据物料ID查询物料信息
            // TODO: 根据入库出库记录统计本期入库出库数量

            reportList.add(report);
        }

        return reportList;
    }

    @Override
    public List<InboundOrder> inboundReport(Long warehouseId, Long supplierId,
                                           LocalDate startDate, LocalDate endDate) {
        QueryWrapper<InboundOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_type", 1); // 采购入库
        if (warehouseId != null) {
            queryWrapper.eq("warehouse_id", warehouseId);
        }
        if (supplierId != null) {
            queryWrapper.eq("supplier_id", supplierId);
        }
        if (startDate != null && endDate != null) {
            queryWrapper.between("create_time", startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        }
        queryWrapper.orderByDesc("create_time");
        return inboundOrderMapper.selectList(queryWrapper);
    }

    @Override
    public List<OutboundOrder> outboundReport(Long warehouseId, Long customerId,
                                              LocalDate startDate, LocalDate endDate) {
        QueryWrapper<OutboundOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_type", 1); // 销售出库
        if (warehouseId != null) {
            queryWrapper.eq("warehouse_id", warehouseId);
        }
        if (customerId != null) {
            queryWrapper.eq("customer_id", customerId);
        }
        if (startDate != null && endDate != null) {
            queryWrapper.between("create_time", startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        }
        queryWrapper.orderByDesc("create_time");
        return outboundOrderMapper.selectList(queryWrapper);
    }
}
