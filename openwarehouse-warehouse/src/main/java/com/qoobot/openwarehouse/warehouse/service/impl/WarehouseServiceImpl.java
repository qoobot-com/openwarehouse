package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Warehouse;
import com.qoobot.openwarehouse.warehouse.mapper.WarehouseMapper;
import com.qoobot.openwarehouse.warehouse.service.IWarehouseService;
import org.springframework.stereotype.Service;

/**
 * 仓库服务实现
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements IWarehouseService {
}
