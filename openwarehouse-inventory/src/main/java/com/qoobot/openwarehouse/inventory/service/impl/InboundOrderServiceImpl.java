package com.qoobot.openwarehouse.inventory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qoobot.openwarehouse.inventory.entity.InboundOrder;
import com.qoobot.openwarehouse.inventory.mapper.InboundOrderMapper;
import com.qoobot.openwarehouse.inventory.service.IInboundOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 入库单服务实现
 */
@Service
@RequiredArgsConstructor
public class InboundOrderServiceImpl extends ServiceImpl<InboundOrderMapper, InboundOrder> implements IInboundOrderService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public String generateOrderNo() {
        String datePrefix = LocalDateTime.now().format(FORMATTER);
        // 简单生成单号: IB + 日期 + 6位序号
        // 实际应该使用数据库序列或Redis生成
        long count = this.lambdaQuery()
                .likeRight(InboundOrder::getOrderNo, "IB" + datePrefix)
                .count();
        String sequence = String.format("%06d", count + 1);
        return "IB" + datePrefix + sequence;
    }
}
