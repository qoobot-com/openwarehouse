package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 其他入库单明细实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("other_inbound_order_detail")
public class OtherInboundOrderDetail extends BaseEntity {

    /**
     * 明细ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 入库单ID
     */
    private Long orderId;

    /**
     * 入库单号
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
     * 入库数量
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
     * 目标库位ID
     */
    private Long locationId;

    /**
     * 目标库位编码
     */
    private String locationCode;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 生产日期
     */
    private String productionDate;

    /**
     * 有效期至
     */
    private String expiryDate;

    /**
     * 备注
     */
    private String remark;
}
