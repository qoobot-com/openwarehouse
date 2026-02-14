package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.inventory.entity.StockCheckOrder;
import com.openwarehouse.inventory.mapper.StockCheckOrderMapper;
import com.openwarehouse.inventory.service.StockCheckOrderService;
import org.springframework.stereotype.Service;

/**
 * 盘点单Service实现类
 */
@Service
public class StockCheckOrderServiceImpl extends ServiceImpl<StockCheckOrderMapper, StockCheckOrder>
        implements StockCheckOrderService {
}
