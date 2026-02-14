package com.openwarehouse.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存预警实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("inventory_alert")
public class InventoryAlert extends BaseEntity {

    /**
     * 预警ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 当前库存数量
     */
    private Integer currentQuantity;

    /**
     * 安全库存下限
     */
    private Integer minQuantity;

    /**
     * 安全库存上限
     */
    private Integer maxQuantity;

    /**
     * 预警类型: 1-低于下限 2-高于上限
     */
    private Integer alertType;

    /**
     * 预警状态: 0-未处理 1-已处理
     */
    private Integer alertStatus;

    /**
     * 处理人ID
     */
    private Long handlerId;

    /**
     * 处理人姓名
     */
    private String handlerName;

    /**
     * 处理时间
     */
    private String handleTime;

    /**
     * 处理说明
     */
    private String handleRemark;

    /**
     * 预警级别: 1-一般 2-重要 3-紧急
     */
    private Integer alertLevel;
}
