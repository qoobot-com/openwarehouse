package com.qoobot.openwarehouse.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.inventory.entity.Inventory;

import java.math.BigDecimal;

/**
 * 库存服务接口
 */
public interface IInventoryService extends IService<Inventory> {

    /**
     * 更新库存数量
     */
    boolean updateQuantity(Long inventoryId, BigDecimal quantity);

    /**
     * 获取仓库物料库存
     */
    Inventory getWarehouseMaterialInventory(Long warehouseId, Long materialId);
}
