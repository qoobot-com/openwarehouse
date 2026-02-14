package com.qoobot.openwarehouse.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 库存查询响应
 */
@Data
@Schema(description = "库存查询响应")
public class InventoryQueryResponse {

    @Schema(description = "库存ID")
    private Long id;

    @Schema(description = "仓库名称")
    private String warehouseName;

    @Schema(description = "物料编码")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "规格")
    private String spec;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "库存数量")
    private BigDecimal quantity;

    @Schema(description = "可用数量")
    private BigDecimal availableQuantity;

    @Schema(description = "锁定数量")
    private BigDecimal lockedQuantity;

    @Schema(description = "库存状态")
    private String status;
}
