package com.qoobot.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qoobot.openwarehouse.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("supplier")
@Schema(description = "供应商")
public class Supplier extends BaseEntity {

    @TableId
    @Schema(description = "供应商ID")
    private Long id;

    @Schema(description = "供应商编码")
    private String supplierCode;

    @Schema(description = "供应商名称")
    private String supplierName;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    private String contactEmail;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "税号")
    private String taxNo;

    @Schema(description = "信用等级(1-5级)")
    private Integer creditLevel;

    @Schema(description = "状态(0-禁用 1-启用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
