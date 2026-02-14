package com.qoobot.openwarehouse.warehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.warehouse.entity.MaterialCategory;

import java.util.List;

/**
 * 物料分类服务接口
 */
public interface IMaterialCategoryService extends IService<MaterialCategory> {

    /**
     * 获取分类树
     */
    List<MaterialCategory> getCategoryTree();
}
