package com.qoobot.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.inventory.entity.Inventory;
import com.qoobot.openwarehouse.inventory.mapper.InventoryMapper;
import com.qoobot.openwarehouse.inventory.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 库存服务实现
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    @Override
    public boolean updateQuantity(Long inventoryId, BigDecimal quantity) {
        Inventory inventory = this.getById(inventoryId);
        if (inventory == null) {
            return false;
        }
        inventory.setQuantity(quantity);
        inventory.setAvailableQuantity(quantity);
        return this.updateById(inventory);
    }

    @Override
    public Inventory getWarehouseMaterialInventory(Long warehouseId, Long materialId) {
        LambdaQueryWrapper<Inventory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Inventory::getWarehouseId, warehouseId)
               .eq(Inventory::getMaterialId, materialId);
        return this.getOne(wrapper);
    }
}
