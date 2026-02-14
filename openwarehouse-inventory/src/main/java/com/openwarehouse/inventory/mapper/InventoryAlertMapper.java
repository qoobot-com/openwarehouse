package com.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.inventory.entity.InventoryAlert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库存预警Mapper接口
 */
@Mapper
public interface InventoryAlertMapper extends BaseMapper<InventoryAlert> {
}
