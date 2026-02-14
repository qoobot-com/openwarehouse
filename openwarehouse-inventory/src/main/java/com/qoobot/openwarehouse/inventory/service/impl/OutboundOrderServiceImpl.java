package com.qoobot.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.inventory.entity.OutboundOrder;
import com.qoobot.openwarehouse.inventory.mapper.OutboundOrderMapper;
import com.qoobot.openwarehouse.inventory.service.IOutboundOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 出库单服务实现
 */
@Service
@RequiredArgsConstructor
public class OutboundOrderServiceImpl extends ServiceImpl<OutboundOrderMapper, OutboundOrder> implements IOutboundOrderService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String generateOrderNo() {
        String datePrefix = LocalDateTime.now().format(FORMATTER);
        // 简单生成单号: OB + 日期 + 6位序号
        long count = this.lambdaQuery()
                .likeRight(OutboundOrder::getOrderNo, "OB" + datePrefix)
                .count();
        String sequence = String.format("%06d", count + 1);
        return "OB" + datePrefix + sequence;
    }
}
