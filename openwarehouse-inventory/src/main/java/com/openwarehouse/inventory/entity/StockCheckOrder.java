package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 盘点单实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock_check_order")
public class StockCheckOrder extends BaseEntity {

    /**
     * 盘点单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 盘点单号
     */
    private String checkNo;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 盘点类型: 1-全面盘点 2-部分盘点 3-动态盘点
     */
    private Integer checkType;

    /**
     * 盘点状态: 0-待盘点 1-盘点中 2-已完成 3-已作废
     */
    private Integer checkStatus;

    /**
     * 计划盘点日期
     */
    private String planCheckDate;

    /**
     * 实际盘点日期
     */
    private String actualCheckDate;

    /**
     * 盘点人ID
     */
    private Long checkerId;

    /**
     * 盘点人姓名
     */
    private String checkerName;

    /**
     * 审核人ID
     */
    private Long auditorId;

    /**
     * 审核人姓名
     */
    private String auditorName;

    /**
     * 账面数量
     */
    private Integer bookQuantity;

    /**
     * 实盘数量
     */
    private Integer actualQuantity;

    /**
     * 差异数量
     */
    private Integer diffQuantity;

    /**
     * 差异金额
     */
    private Double diffAmount;

    /**
     * 备注
     */
    private String remark;
}
