package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wh_warehouse")
@Schema(description = "仓库")
public class Warehouse extends BaseEntity {

    @TableId
    @Schema(description = "仓库ID")
    private Long id;

    @Schema(description = "仓库编码")
    private String warehouseCode;

    @Schema(description = "仓库名称")
    private String warehouseName;

    @Schema(description = "仓库类型(1-普通仓库 2-冷链仓库 3-危险品仓库)")
    private Integer warehouseType;

    @Schema(description = "仓库地址")
    private String address;

    @Schema(description = "负责人ID")
    private Long managerId;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
