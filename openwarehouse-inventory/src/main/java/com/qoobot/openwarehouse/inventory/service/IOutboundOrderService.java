package com.qoobot.openwarehouse.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.inventory.entity.OutboundOrder;

/**
 * 出库单服务接口
 */
public interface IOutboundOrderService extends IService<OutboundOrder> {

    /**
     * 生成出库单号
     */
    String generateOrderNo();
}
