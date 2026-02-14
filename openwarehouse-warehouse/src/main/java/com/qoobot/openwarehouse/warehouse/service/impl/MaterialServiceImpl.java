package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.Material;
import com.qoobot.openwarehouse.warehouse.mapper.MaterialMapper;
import com.qoobot.openwarehouse.warehouse.service.IMaterialService;
import org.springframework.stereotype.Service;

/**
 * 物料服务实现
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
}
