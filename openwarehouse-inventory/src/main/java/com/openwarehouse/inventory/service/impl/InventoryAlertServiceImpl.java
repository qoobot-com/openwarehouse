package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.inventory.entity.InventoryAlert;
import com.openwarehouse.inventory.mapper.InventoryAlertMapper;
import com.openwarehouse.inventory.service.InventoryAlertService;
import org.springframework.stereotype.Service;

/**
 * 库存预警Service实现类
 */
@Service
public class InventoryAlertServiceImpl extends ServiceImpl<InventoryAlertMapper, InventoryAlert>
        implements InventoryAlertService {
}
