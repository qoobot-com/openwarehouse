package com.qoobot.openwarehouse.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 入库单创建请求
 */
@Data
@Schema(description = "入库单创建请求")
public class InboundOrderCreateRequest {

    @Schema(description = "仓库ID", required = true)
    @NotNull(message = "仓库ID不能为空")
    private Long warehouseId;

    @Schema(description = "入库类型(1-采购入库 2-退货入库 3-调拨入库 4-其他入库)", required = true)
    @NotNull(message = "入库类型不能为空")
    private Integer orderType;

    @Schema(description = "供应商ID")
    private Long supplierId;

    @Schema(description = "预计入库日期")
    private LocalDate expectDate;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "入库明细")
    private List<InboundOrderDetailRequest> details;
}
