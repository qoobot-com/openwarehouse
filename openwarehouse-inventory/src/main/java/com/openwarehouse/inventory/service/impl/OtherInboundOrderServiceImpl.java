package com.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.openwarehouse.inventory.entity.OtherInboundOrder;
import com.openwarehouse.inventory.mapper.OtherInboundOrderMapper;
import com.openwarehouse.inventory.service.OtherInboundOrderService;
import org.springframework.stereotype.Service;

/**
 * 其他入库单Service实现类
 */
@Service
public class OtherInboundOrderServiceImpl extends ServiceImpl<OtherInboundOrderMapper, OtherInboundOrder>
        implements OtherInboundOrderService {
}
