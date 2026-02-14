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
 * 入库单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inbound_order")
@Schema(description = "入库单")
public class InboundOrder extends BaseEntity {

    @TableId
    @Schema(description = "入库单ID")
    private Long id;

    @Schema(description = "入库单号")
    private String orderNo;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "入库类型(1-采购入库 2-退货入库 3-调拨入库 4-其他入库)")
    private Integer orderType;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "状态(0-待入库 1-部分入库 2-已完成)")
    private Integer status;

    @Schema(description = "预计入库日期")
    private LocalDate expectDate;

    @Schema(description = "实际入库日期")
    private LocalDate actualDate;

    @Schema(description = "总数量")
    private BigDecimal totalQuantity;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "备注")
    private String remark;
}
