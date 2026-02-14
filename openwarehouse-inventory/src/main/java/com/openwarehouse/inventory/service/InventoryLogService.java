package com.openwarehouse.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.openwarehouse.inventory.entity.InventoryLog;

/**
 * 库存变动记录Service接口
 */
public interface InventoryLogService extends IService<InventoryLog> {

    /**
     * 记录入库变动
     */
    void recordInbound(Long warehouseId, Long materialId, Long locationId,
                       String orderNo, Double quantity, Double beforeQuantity,
                       Double afterQuantity, String remark);

    /**
     * 记录出库变动
     */
    void recordOutbound(Long warehouseId, Long materialId, Long locationId,
                        String orderNo, Double quantity, Double beforeQuantity,
                        Double afterQuantity, String remark);

    /**
     * 记录盘点变动
     */
    void recordCheck(Long warehouseId, Long materialId, Long locationId,
                     String orderNo, Double quantity, Double beforeQuantity,
                     Double afterQuantity, String remark);
}
