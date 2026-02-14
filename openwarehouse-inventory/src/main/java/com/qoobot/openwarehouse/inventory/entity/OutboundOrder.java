package com.qoobot.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 出库单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("outbound_order")
@Schema(description = "出库单")
public class OutboundOrder extends BaseEntity {

    @TableId
    @Schema(description = "出库单ID")
    private Long id;

    @Schema(description = "出库单号")
    private String orderNo;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "出库类型(1-销售出库 2-报废出库 3-调拨出库 4-其他出库)")
    private Integer orderType;

    @Schema(description = "客户ID")
    private Long customerId;

    @Schema(description = "状态(0-待出库 1-部分出库 2-已完成)")
    private Integer status;

    @Schema(description = "预计出库日期")
    private LocalDate expectDate;

    @Schema(description = "实际出库日期")
    private LocalDate actualDate;

    @Schema(description = "总数量")
    private BigDecimal totalQuantity;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "备注")
    private String remark;
}
