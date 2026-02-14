package com.qoobot.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inventory")
@Schema(description = "库存")
public class Inventory extends BaseEntity {

    @TableId
    @Schema(description = "库存ID")
    private Long id;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "物料ID")
    private Long materialId;

    @Schema(description = "库位ID")
    private Long locationId;

    @Schema(description = "库存数量")
    private BigDecimal quantity;

    @Schema(description = "可用数量")
    private BigDecimal availableQuantity;

    @Schema(description = "锁定数量")
    private BigDecimal lockedQuantity;

    @Schema(description = "最小库存")
    private BigDecimal minQuantity;

    @Schema(description = "最大库存")
    private BigDecimal maxQuantity;
}
