package com.qoobot.openwarehouse.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.warehouse.entity.MaterialCategory;
import com.qoobot.openwarehouse.warehouse.mapper.MaterialCategoryMapper;
import com.qoobot.openwarehouse.warehouse.service.IMaterialCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物料分类服务实现
 */
@Service
public class MaterialCategoryServiceImpl extends ServiceImpl<MaterialCategoryMapper, MaterialCategory> implements IMaterialCategoryService {

    @Override
    public List<MaterialCategory> getCategoryTree() {
        // TODO: 实现分类树构建逻辑
        return this.list();
    }
}
