package com.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.inventory.entity.StockCheckDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 盘点单明细Mapper接口
 */
@Mapper
public interface StockCheckDetailMapper extends BaseMapper<StockCheckDetail> {
}
