package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 盘点单明细实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock_check_detail")
public class StockCheckDetail extends BaseEntity {

    /**
     * 明细ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 盘点单ID
     */
    private Long orderId;

    /**
     * 盘点单号
     */
    private String checkNo;

    /**
     * 物料ID
     */
    private Long materialId;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料规格
     */
    private String specification;

    /**
     * 单位
     */
    private String unit;

    /**
     * 库位ID
     */
    private Long locationId;

    /**
     * 库位编码
     */
    private String locationCode;

    /**
     * 批次号
     */
    private String batchNo;

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
     * 差异类型: 1-盘盈 2-盘亏 3-无差异
     */
    private Integer diffType;

    /**
     * 处理状态: 0-待处理 1-已处理
     */
    private Integer handleStatus;

    /**
     * 备注
     */
    private String remark;
}
