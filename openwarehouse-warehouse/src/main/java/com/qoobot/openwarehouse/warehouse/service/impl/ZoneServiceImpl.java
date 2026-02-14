package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Zone;
import com.qoobot.openwarehouse.warehouse.mapper.ZoneMapper;
import com.qoobot.openwarehouse.warehouse.service.IZoneService;
import org.springframework.stereotype.Service;

/**
 * 库区服务实现
 */
@Service
public class ZoneServiceImpl extends ServiceImpl<ZoneMapper, Zone> implements IZoneService {
}
