package com.qoobot.openwarehouse.warehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qoobot.openwarehouse.warehouse.entity.Supplier;
import org.apache.ibatis.annotations.Mapper;

/**
 * 供应商Mapper
 */
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {
}
