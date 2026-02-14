package com.qoobot.openwarehouse.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 入库单明细请求
 */
@Data
@Schema(description = "入库单明细请求")
public class InboundOrderDetailRequest {

    @Schema(description = "物料ID", required = true)
    @NotNull(message = "物料ID不能为空")
    private Long materialId;

    @Schema(description = "库位ID")
    private Long locationId;

    @Schema(description = "数量", required = true)
    @NotNull(message = "数量不能为空")
    private BigDecimal quantity;

    @Schema(description = "单价")
    private BigDecimal unitPrice;

    @Schema(description = "批次号")
    private String batchNo;

    @Schema(description = "生产日期")
    private LocalDate productionDate;

    @Schema(description = "有效期")
    private LocalDate expiryDate;

    @Schema(description = "备注")
    private String remark;
}
