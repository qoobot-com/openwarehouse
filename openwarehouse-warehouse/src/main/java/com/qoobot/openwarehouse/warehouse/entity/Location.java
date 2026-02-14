package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库位实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wh_location")
@Schema(description = "库位")
public class Location extends BaseEntity {

    @TableId
    @Schema(description = "库位ID")
    private Long id;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "库区ID")
    private Long zoneId;

    @Schema(description = "货架ID")
    private Long shelfId;

    @Schema(description = "库位编码")
    private String locationCode;

    @Schema(description = "库位名称")
    private String locationName;

    @Schema(description = "行号")
    private Integer rowIndex;

    @Schema(description = "列号")
    private Integer columnIndex;

    @Schema(description = "层号")
    private Integer layerIndex;

    @Schema(description = "库位类型(1-普通库位 2-拣货库位 3-暂存库位)")
    private Integer locationType;

    @Schema(description = "容量")
    private BigDecimal capacity;

    @Schema(description = "已用容量")
    private BigDecimal usedCapacity;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
