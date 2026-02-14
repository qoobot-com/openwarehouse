package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Shelf;
import com.qoobot.openwarehouse.warehouse.mapper.ShelfMapper;
import com.qoobot.openwarehouse.warehouse.service.IShelfService;
import org.springframework.stereotype.Service;

/**
 * 货架服务实现
 */
@Service
public class ShelfServiceImpl extends ServiceImpl<ShelfMapper, Shelf> implements IShelfService {
}
