package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 其他入库单实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("other_inbound_order")
public class OtherInboundOrder extends BaseEntity {

    /**
     * 入库单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 入库单号
     */
    private String orderNo;

    /**
     * 入库类型: 1-退货入库 2-转库入库 3-调拨入库 4-盘点入库 9-其他
     */
    private Integer type;

    /**
     * 来源仓库ID
     */
    private Long sourceWarehouseId;

    /**
     * 来源仓库名称
     */
    private String sourceWarehouseName;

    /**
     * 目标仓库ID
     */
    private Long warehouseId;

    /**
     * 目标仓库名称
     */
    private String warehouseName;

    /**
     * 经办人ID
     */
    private Long operatorId;

    /**
     * 经办人姓名
     */
    private String operatorName;

    /**
     * 审批人ID
     */
    private Long approverId;

    /**
     * 审批人姓名
     */
    private String approverName;

    /**
     * 入库日期
     */
    private String inboundDate;

    /**
     * 状态: 0-待审批 1-已审批 2-已入库 3-已取消
     */
    private Integer status;

    /**
     * 总数量
     */
    private Integer totalQuantity;

    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 备注
     */
    private String remark;
}
