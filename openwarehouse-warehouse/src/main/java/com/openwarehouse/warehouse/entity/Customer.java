package com.openwarehouse.warehouse.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.openwarehouse.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("customer")
public class Customer extends BaseEntity {

    /**
     * 客户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户编码
     */
    private String customerCode;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户类型: 1-企业客户 2-个人客户
     */
    private Integer customerType;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 信用等级: 1-AAA 2-AA 3-A 4-B 5-C
     */
    private Integer creditLevel;

    /**
     * 信用额度
     */
    private Double creditLimit;

    /**
     * 已用信用额度
     */
    private Double usedCredit;

    /**
     * 结算方式: 1-现结 2-月结 3-季结 4-账期结算
     */
    private Integer paymentMethod;

    /**
     * 账期天数
     */
    private Integer paymentDays;

    /**
     * 状态: 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
