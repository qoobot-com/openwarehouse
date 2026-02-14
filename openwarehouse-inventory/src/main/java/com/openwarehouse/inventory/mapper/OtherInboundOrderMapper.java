package com.openwarehouse.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.openwarehouse.inventory.entity.OtherInboundOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 其他入库单Mapper接口
 */
@Mapper
public interface OtherInboundOrderMapper extends BaseMapper<OtherInboundOrder> {
}
