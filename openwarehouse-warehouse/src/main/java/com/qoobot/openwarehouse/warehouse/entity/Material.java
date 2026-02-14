package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物料实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material")
@Schema(description = "物料")
public class Material extends BaseEntity {

    @TableId
    @Schema(description = "物料ID")
    private Long id;

    @Schema(description = "物料编码")
    private String materialCode;

    @Schema(description = "物料名称")
    private String materialName;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "规格型号")
    private String spec;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "条码")
    private String barcode;

    @Schema(description = "二维码")
    private String qrCode;

    @Schema(description = "品牌")
    private String brand;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
