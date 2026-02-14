package com.qoobot.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qoobot.openwarehouse.inventory.entity.InboundOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 入库单Mapper
 */
@Mapper
public interface InboundOrderMapper extends BaseMapper<InboundOrder> {
}
