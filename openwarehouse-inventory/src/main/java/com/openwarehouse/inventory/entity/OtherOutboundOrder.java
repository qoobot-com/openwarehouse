package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 其他出库单实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("other_outbound_order")
public class OtherOutboundOrder extends BaseEntity {

    /**
     * 出库单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 出库单号
     */
    private String orderNo;

    /**
     * 出库类型: 1-报废出库 2-调拨出库 3-损耗出库 4-转库出库 9-其他
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
    private Long targetWarehouseId;

    /**
     * 目标仓库名称
     */
    private String targetWarehouseName;

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
     * 出库日期
     */
    private String outboundDate;

    /**
     * 状态: 0-待审批 1-已审批 2-已出库 3-已取消
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
