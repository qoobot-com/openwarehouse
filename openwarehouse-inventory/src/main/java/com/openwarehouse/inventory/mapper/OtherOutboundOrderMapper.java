package com.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.inventory.entity.OtherOutboundOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 其他出库单Mapper接口
 */
@Mapper
public interface OtherOutboundOrderMapper extends BaseMapper<OtherOutboundOrder> {
}
