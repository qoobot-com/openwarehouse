package com.qoobot.openwarehouse.inventory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qoobot.openwarehouse.inventory.entity.InboundOrder;

/**
 * 入库单服务接口
 */
public interface IInboundOrderService extends IService<InboundOrder> {

    /**
     * 生成入库单号
     */
    String generateOrderNo();
}
