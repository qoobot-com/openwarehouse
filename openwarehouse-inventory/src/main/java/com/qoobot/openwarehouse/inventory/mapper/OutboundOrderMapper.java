package com.qoobot.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qoobot.openwarehouse.inventory.entity.OutboundOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出库单Mapper
 */
@Mapper
public interface OutboundOrderMapper extends BaseMapper<OutboundOrder> {
}
