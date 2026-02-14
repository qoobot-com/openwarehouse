package com.openwarehouse.inventory.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 库存报表实体类
 */
@Data
public class InventoryReport {

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

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
     * 期初数量
     */
    private BigDecimal startQuantity;

    /**
     * 本期入库数量
     */
    private BigDecimal inboundQuantity;

    /**
     * 本期出库数量
     */
    private BigDecimal outboundQuantity;

    /**
     * 期末库存数量
     */
    private BigDecimal endQuantity;

    /**
     * 期初金额
     */
    private BigDecimal startAmount;

    /**
     * 本期入库金额
     */
    private BigDecimal inboundAmount;

    /**
     * 本期出库金额
     */
    private BigDecimal outboundAmount;

    /**
     * 期末库存金额
     */
    private BigDecimal endAmount;
}
