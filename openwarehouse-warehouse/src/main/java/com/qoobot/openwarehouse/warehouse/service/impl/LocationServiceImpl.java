package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Location;
import com.qoobot.openwarehouse.warehouse.mapper.LocationMapper;
import com.qoobot.openwarehouse.warehouse.service.ILocationService;
import org.springframework.stereotype.Service;

/**
 * 库位服务实现
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {
}
