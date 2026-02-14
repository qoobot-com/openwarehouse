package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物料分类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("material_category")
@Schema(description = "物料分类")
public class MaterialCategory extends BaseEntity {

    @TableId
    @Schema(description = "分类ID")
    private Long id;

    @Schema(description = "父分类ID")
    private Long parentId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "分类编码")
    private String categoryCode;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;
}
