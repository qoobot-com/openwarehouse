package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 货架实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wh_shelf")
@Schema(description = "货架")
public class Shelf extends BaseEntity {

    @TableId
    @Schema(description = "货架ID")
    private Long id;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "库区ID")
    private Long zoneId;

    @Schema(description = "货架编码")
    private String shelfCode;

    @Schema(description = "货架名称")
    private String shelfName;

    @Schema(description = "货架类型(1-单面货架 2-双面货架 3-多层货架)")
    private Integer shelfType;

    @Schema(description = "行数")
    private Integer rowNum;

    @Schema(description = "列数")
    private Integer columnNum;

    @Schema(description = "层数")
    private Integer layerNum;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
