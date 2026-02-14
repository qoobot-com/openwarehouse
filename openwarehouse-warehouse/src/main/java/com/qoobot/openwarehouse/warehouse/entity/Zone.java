package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库区实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wh_zone")
@Schema(description = "库区")
public class Zone extends BaseEntity {

    @TableId
    @Schema(description = "库区ID")
    private Long id;

    @Schema(description = "仓库ID")
    private Long warehouseId;

    @Schema(description = "库区编码")
    private String zoneCode;

    @Schema(description = "库区名称")
    private String zoneName;

    @Schema(description = "库区类型(1-收货区 2-存储区 3-发货区 4-退货区)")
    private Integer zoneType;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
