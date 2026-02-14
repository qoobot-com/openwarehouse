package com.qoobot.openwarehouse.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qoobot.openwarehouse.warehouse.entity.Material;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物料Mapper
 */
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
}
