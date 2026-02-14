package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.inventory.entity.InventoryLog;
import com.openwarehouse.inventory.mapper.InventoryLogMapper;
import com.openwarehouse.inventory.service.InventoryLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存变动记录Service实现类
 */
@Service
public class InventoryLogServiceImpl extends ServiceImpl<InventoryLogMapper, InventoryLog>
        implements InventoryLogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordInbound(Long warehouseId, Long materialId, Long locationId,
                              String orderNo, Double quantity, Double beforeQuantity,
                              Double afterQuantity, String remark) {
        InventoryLog log = new InventoryLog();
        log.setWarehouseId(warehouseId);
        log.setMaterialId(materialId);
        log.setLocationId(locationId);
        log.setOrderNo(orderNo);
        log.setOrderType(1); // 入库
        log.setChangeType(1); // 增加
        log.setChangeQuantity(quantity);
        log.setBeforeQuantity(beforeQuantity);
        log.setAfterQuantity(afterQuantity);
        log.setRemark(remark);
        this.save(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordOutbound(Long warehouseId, Long materialId, Long locationId,
                               String orderNo, Double quantity, Double beforeQuantity,
                               Double afterQuantity, String remark) {
        InventoryLog log = new InventoryLog();
        log.setWarehouseId(warehouseId);
        log.setMaterialId(materialId);
        log.setLocationId(locationId);
        log.setOrderNo(orderNo);
        log.setOrderType(2); // 出库
        log.setChangeType(2); // 减少
        log.setChangeQuantity(quantity);
        log.setBeforeQuantity(beforeQuantity);
        log.setAfterQuantity(afterQuantity);
        log.setRemark(remark);
        this.save(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordCheck(Long warehouseId, Long materialId, Long locationId,
                            String orderNo, Double quantity, Double beforeQuantity,
                            Double afterQuantity, String remark) {
        InventoryLog log = new InventoryLog();
        log.setWarehouseId(warehouseId);
        log.setMaterialId(materialId);
        log.setLocationId(locationId);
        log.setOrderNo(orderNo);
        log.setOrderType(3); // 盘点
        log.setChangeType(quantity > 0 ? 1 : 2); // 根据正负判断增加或减少
        log.setChangeQuantity(quantity);
        log.setBeforeQuantity(beforeQuantity);
        log.setAfterQuantity(afterQuantity);
        log.setRemark(remark);
        this.save(log);
    }
}
