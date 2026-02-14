package com.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.inventory.entity.StockCheckOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 盘点单Mapper接口
 */
@Mapper
public interface StockCheckOrderMapper extends BaseMapper<StockCheckOrder> {
}
