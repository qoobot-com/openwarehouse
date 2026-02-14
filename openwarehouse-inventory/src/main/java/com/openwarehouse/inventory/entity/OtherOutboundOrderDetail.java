package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 其他出库单明细实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("other_outbound_order_detail")
public class OtherOutboundOrderDetail extends BaseEntity {

    /**
     * 明细ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 出库单ID
     */
    private Long orderId;

    /**
     * 出库单号
     */
    private String orderNo;

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
     * 出库数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 来源库位ID
     */
    private Long sourceLocationId;

    /**
     * 来源库位编码
     */
    private String sourceLocationCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 备注
     */
    private String remark;
}
