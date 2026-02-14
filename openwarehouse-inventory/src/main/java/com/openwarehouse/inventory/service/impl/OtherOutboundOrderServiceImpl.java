package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.inventory.entity.OtherOutboundOrder;
import com.openwarehouse.inventory.mapper.OtherOutboundOrderMapper;
import com.openwarehouse.inventory.service.OtherOutboundOrderService;
import org.springframework.stereotype.Service;

/**
 * 其他出库单Service实现类
 */
@Service
public class OtherOutboundOrderServiceImpl extends ServiceImpl<OtherOutboundOrderMapper, OtherOutboundOrder>
        implements OtherOutboundOrderService {
}
