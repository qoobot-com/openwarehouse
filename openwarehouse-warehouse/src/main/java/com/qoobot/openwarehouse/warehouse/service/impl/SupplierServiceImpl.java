package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Supplier;
import com.qoobot.openwarehouse.warehouse.mapper.SupplierMapper;
import com.qoobot.openwarehouse.warehouse.service.ISupplierService;
import org.springframework.stereotype.Service;

/**
 * 供应商服务实现
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
}
