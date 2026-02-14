-- ========================================
-- OpenWarehouse 数据库初始化脚本
-- ========================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `openwarehouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `openwarehouse`;

-- ========================================
-- 系统管理模块表
-- ========================================

-- 用户表
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `real_name` VARCHAR(50) NOT NULL COMMENT '真实姓名',
  `phone` VARCHAR(20) COMMENT '手机号',
  `email` VARCHAR(100) COMMENT '邮箱',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码',
  `description` VARCHAR(200) COMMENT '角色描述',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 用户角色关联表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `role_id` BIGINT NOT NULL COMMENT '角色ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 菜单表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` BIGINT DEFAULT 0 COMMENT '父菜单ID',
  `menu_name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
  `menu_type` TINYINT NOT NULL COMMENT '菜单类型(1-目录 2-菜单 3-按钮)',
  `path` VARCHAR(200) COMMENT '路由路径',
  `component` VARCHAR(200) COMMENT '组件路径',
  `icon` VARCHAR(100) COMMENT '菜单图标',
  `permission` VARCHAR(100) COMMENT '权限标识',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- 角色菜单关联表
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` BIGINT NOT NULL COMMENT '角色ID',
  `menu_id` BIGINT NOT NULL COMMENT '菜单ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_menu` (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联表';

-- ========================================
-- 认证授权表
-- ========================================

-- 用户令牌表
DROP TABLE IF EXISTS `auth_token`;
CREATE TABLE `auth_token` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `token` VARCHAR(500) NOT NULL COMMENT '令牌',
  `expire_time` DATETIME NOT NULL COMMENT '过期时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_token` (`token`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户令牌表';

-- ========================================
-- 仓储管理模块表
-- ========================================

-- 仓库表
DROP TABLE IF EXISTS `wh_warehouse`;
CREATE TABLE `wh_warehouse` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
  `warehouse_code` VARCHAR(50) NOT NULL COMMENT '仓库编码',
  `warehouse_name` VARCHAR(100) NOT NULL COMMENT '仓库名称',
  `warehouse_type` TINYINT NOT NULL COMMENT '仓库类型(1-普通仓库 2-冷链仓库 3-危险品仓库)',
  `address` VARCHAR(200) COMMENT '仓库地址',
  `manager_id` BIGINT COMMENT '负责人ID',
  `contact_phone` VARCHAR(20) COMMENT '联系电话',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_warehouse_code` (`warehouse_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

-- 库区表
DROP TABLE IF EXISTS `wh_zone`;
CREATE TABLE `wh_zone` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库区ID',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `zone_code` VARCHAR(50) NOT NULL COMMENT '库区编码',
  `zone_name` VARCHAR(100) NOT NULL COMMENT '库区名称',
  `zone_type` TINYINT NOT NULL COMMENT '库区类型(1-收货区 2-存储区 3-发货区 4-退货区)',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_zone_code` (`zone_code`),
  KEY `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库区表';

-- 货架表
DROP TABLE IF EXISTS `wh_shelf`;
CREATE TABLE `wh_shelf` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '货架ID',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `zone_id` BIGINT NOT NULL COMMENT '库区ID',
  `shelf_code` VARCHAR(50) NOT NULL COMMENT '货架编码',
  `shelf_name` VARCHAR(100) COMMENT '货架名称',
  `shelf_type` TINYINT NOT NULL COMMENT '货架类型(1-单面货架 2-双面货架 3-多层货架)',
  `row_num` INT COMMENT '行数',
  `column_num` INT COMMENT '列数',
  `layer_num` INT COMMENT '层数',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_shelf_code` (`shelf_code`),
  KEY `idx_warehouse_id` (`warehouse_id`),
  KEY `idx_zone_id` (`zone_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='货架表';

-- 库位表
DROP TABLE IF EXISTS `wh_location`;
CREATE TABLE `wh_location` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库位ID',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `zone_id` BIGINT NOT NULL COMMENT '库区ID',
  `shelf_id` BIGINT NOT NULL COMMENT '货架ID',
  `location_code` VARCHAR(50) NOT NULL COMMENT '库位编码',
  `location_name` VARCHAR(100) COMMENT '库位名称',
  `row_index` INT COMMENT '行号',
  `column_index` INT COMMENT '列号',
  `layer_index` INT COMMENT '层号',
  `location_type` TINYINT COMMENT '库位类型(1-普通库位 2-拣货库位 3-暂存库位)',
  `capacity` DECIMAL(10,2) COMMENT '容量',
  `used_capacity` DECIMAL(10,2) DEFAULT 0 COMMENT '已用容量',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_location_code` (`location_code`),
  KEY `idx_warehouse_id` (`warehouse_id`),
  KEY `idx_zone_id` (`zone_id`),
  KEY `idx_shelf_id` (`shelf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库位表';

-- ========================================
-- 货品管理模块表
-- ========================================

-- 物料表
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '物料ID',
  `material_code` VARCHAR(50) NOT NULL COMMENT '物料编码',
  `material_name` VARCHAR(100) NOT NULL COMMENT '物料名称',
  `category_id` BIGINT COMMENT '分类ID',
  `spec` VARCHAR(200) COMMENT '规格型号',
  `unit` VARCHAR(20) NOT NULL COMMENT '单位',
  `barcode` VARCHAR(100) COMMENT '条码',
  `qr_code` VARCHAR(200) COMMENT '二维码',
  `brand` VARCHAR(100) COMMENT '品牌',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_material_code` (`material_code`),
  UNIQUE KEY `uk_barcode` (`barcode`),
  KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物料表';

-- 物料分类表
DROP TABLE IF EXISTS `material_category`;
CREATE TABLE `material_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id` BIGINT DEFAULT 0 COMMENT '父分类ID',
  `category_name` VARCHAR(100) NOT NULL COMMENT '分类名称',
  `category_code` VARCHAR(50) NOT NULL COMMENT '分类编码',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_category_code` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='物料分类表';

-- ========================================
-- 供应商客户管理表
-- ========================================

-- 供应商表
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplier_code` VARCHAR(50) NOT NULL COMMENT '供应商编码',
  `supplier_name` VARCHAR(100) NOT NULL COMMENT '供应商名称',
  `contact_person` VARCHAR(50) COMMENT '联系人',
  `contact_phone` VARCHAR(20) COMMENT '联系电话',
  `contact_email` VARCHAR(100) COMMENT '联系邮箱',
  `address` VARCHAR(200) COMMENT '地址',
  `tax_no` VARCHAR(50) COMMENT '税号',
  `credit_level` TINYINT COMMENT '信用等级(1-5级)',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_supplier_code` (`supplier_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';

-- ========================================
-- 库存管理模块表
-- ========================================

-- 库存表
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `location_id` BIGINT COMMENT '库位ID',
  `quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '库存数量',
  `available_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '可用数量',
  `locked_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '锁定数量',
  `min_quantity` DECIMAL(10,2) COMMENT '最小库存',
  `max_quantity` DECIMAL(10,2) COMMENT '最大库存',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_warehouse_material_location` (`warehouse_id`, `material_id`, `location_id`),
  KEY `idx_warehouse_id` (`warehouse_id`),
  KEY `idx_material_id` (`material_id`),
  KEY `idx_location_id` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 入库单表
DROP TABLE IF EXISTS `inbound_order`;
CREATE TABLE `inbound_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '入库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '入库单号',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `order_type` TINYINT NOT NULL COMMENT '入库类型(1-采购入库 2-退货入库 3-调拨入库 4-其他入库)',
  `supplier_id` BIGINT COMMENT '供应商ID',
  `status` TINYINT DEFAULT 0 COMMENT '状态(0-待入库 1-部分入库 2-已完成)',
  `expect_date` DATE COMMENT '预计入库日期',
  `actual_date` DATE COMMENT '实际入库日期',
  `total_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '总数量',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '总金额',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_warehouse_id` (`warehouse_id`),
  KEY `idx_supplier_id` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单表';

-- 入库单明细表
DROP TABLE IF EXISTS `inbound_order_detail`;
CREATE TABLE `inbound_order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` BIGINT NOT NULL COMMENT '入库单ID',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `location_id` BIGINT COMMENT '库位ID',
  `quantity` DECIMAL(10,2) NOT NULL COMMENT '数量',
  `unit_price` DECIMAL(10,2) COMMENT '单价',
  `total_price` DECIMAL(10,2) COMMENT '总价',
  `batch_no` VARCHAR(50) COMMENT '批次号',
  `production_date` DATE COMMENT '生产日期',
  `expiry_date` DATE COMMENT '有效期',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_material_id` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入库单明细表';

-- 出库单表
DROP TABLE IF EXISTS `outbound_order`;
CREATE TABLE `outbound_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '出库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '出库单号',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `order_type` TINYINT NOT NULL COMMENT '出库类型(1-销售出库 2-报废出库 3-调拨出库 4-其他出库)',
  `customer_id` BIGINT COMMENT '客户ID',
  `status` TINYINT DEFAULT 0 COMMENT '状态(0-待出库 1-部分出库 2-已完成)',
  `expect_date` DATE COMMENT '预计出库日期',
  `actual_date` DATE COMMENT '实际出库日期',
  `total_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '总数量',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '总金额',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单表';

-- 出库单明细表
DROP TABLE IF EXISTS `outbound_order_detail`;
CREATE TABLE `outbound_order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` BIGINT NOT NULL COMMENT '出库单ID',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `location_id` BIGINT COMMENT '库位ID',
  `quantity` DECIMAL(10,2) NOT NULL COMMENT '数量',
  `unit_price` DECIMAL(10,2) COMMENT '单价',
  `total_price` DECIMAL(10,2) COMMENT '总价',
  `batch_no` VARCHAR(50) COMMENT '批次号',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_material_id` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='出库单明细表';

-- 库存变动记录表
DROP TABLE IF EXISTS `inventory_log`;
CREATE TABLE `inventory_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `location_id` BIGINT COMMENT '库位ID',
  `order_no` VARCHAR(50) COMMENT '单据编号',
  `order_type` TINYINT COMMENT '单据类型(1-入库 2-出库 3-盘点 4-调拨)',
  `change_type` TINYINT NOT NULL COMMENT '变动类型(1-增加 2-减少)',
  `change_quantity` DECIMAL(10,2) NOT NULL COMMENT '变动数量',
  `before_quantity` DECIMAL(10,2) COMMENT '变动前数量',
  `after_quantity` DECIMAL(10,2) COMMENT '变动后数量',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_warehouse_id` (`warehouse_id`),
  KEY `idx_material_id` (`material_id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存变动记录表';

-- 初始化默认管理员账号(密码: admin123)
INSERT INTO `sys_user` (`username`, `password`, `real_name`, `status`) VALUES
('admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE/s9L3bC4qO2u', '系统管理员', 1);

-- 初始化默认角色
INSERT INTO `sys_role` (`role_name`, `role_code`, `description`, `status`) VALUES
('超级管理员', 'ROLE_ADMIN', '系统超级管理员，拥有所有权限', 1),
('仓库管理员', 'ROLE_WAREHOUSE', '仓库管理员，负责仓库日常管理', 1),
('操作员', 'ROLE_OPERATOR', '操作员，负责日常出入库操作', 1);

-- 关联管理员角色
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 1);

-- ========================================
-- 扩展表
-- ========================================

-- 权限表
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_code` VARCHAR(100) NOT NULL COMMENT '权限编码',
  `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
  `permission_type` TINYINT NOT NULL COMMENT '权限类型(1-菜单权限 2-按钮权限 3-接口权限)',
  `menu_id` BIGINT COMMENT '所属菜单ID',
  `request_method` VARCHAR(10) COMMENT '请求方式',
  `request_path` VARCHAR(200) COMMENT '请求路径',
  `sort` INT DEFAULT 0 COMMENT '排序',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 角色权限关联表
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` BIGINT NOT NULL COMMENT '角色ID',
  `permission_id` BIGINT NOT NULL COMMENT '权限ID',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 客户表
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `customer_code` VARCHAR(50) NOT NULL COMMENT '客户编码',
  `customer_name` VARCHAR(100) NOT NULL COMMENT '客户名称',
  `customer_type` TINYINT NOT NULL COMMENT '客户类型(1-企业客户 2-个人客户)',
  `contact_person` VARCHAR(50) COMMENT '联系人',
  `contact_phone` VARCHAR(20) COMMENT '联系电话',
  `contact_email` VARCHAR(100) COMMENT '联系邮箱',
  `province` VARCHAR(50) COMMENT '省份',
  `city` VARCHAR(50) COMMENT '城市',
  `district` VARCHAR(50) COMMENT '区县',
  `address` VARCHAR(200) COMMENT '详细地址',
  `credit_level` TINYINT COMMENT '信用等级(1-AAA 2-AA 3-A 4-B 5-C)',
  `credit_limit` DECIMAL(12,2) COMMENT '信用额度',
  `used_credit` DECIMAL(12,2) DEFAULT 0 COMMENT '已用信用额度',
  `payment_method` TINYINT COMMENT '结算方式(1-现结 2-月结 3-季结 4-账期结算)',
  `payment_days` INT COMMENT '账期天数',
  `status` TINYINT DEFAULT 1 COMMENT '状态(0-禁用 1-启用)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_code` (`customer_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户表';

-- 其他入库单表
DROP TABLE IF EXISTS `other_inbound_order`;
CREATE TABLE `other_inbound_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '入库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '入库单号',
  `type` TINYINT NOT NULL COMMENT '入库类型(1-退货入库 2-转库入库 3-调拨入库 4-盘点入库 9-其他)',
  `source_warehouse_id` BIGINT COMMENT '来源仓库ID',
  `source_warehouse_name` VARCHAR(100) COMMENT '来源仓库名称',
  `warehouse_id` BIGINT NOT NULL COMMENT '目标仓库ID',
  `warehouse_name` VARCHAR(100) COMMENT '目标仓库名称',
  `operator_id` BIGINT COMMENT '经办人ID',
  `operator_name` VARCHAR(50) COMMENT '经办人姓名',
  `approver_id` BIGINT COMMENT '审批人ID',
  `approver_name` VARCHAR(50) COMMENT '审批人姓名',
  `inbound_date` DATE COMMENT '入库日期',
  `status` TINYINT DEFAULT 0 COMMENT '状态(0-待审批 1-已审批 2-已入库 3-已取消)',
  `total_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '总数量',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '总金额',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='其他入库单表';

-- 其他入库单明细表
DROP TABLE IF EXISTS `other_inbound_order_detail`;
CREATE TABLE `other_inbound_order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` BIGINT NOT NULL COMMENT '入库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '入库单号',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `material_code` VARCHAR(50) COMMENT '物料编码',
  `material_name` VARCHAR(100) COMMENT '物料名称',
  `specification` VARCHAR(200) COMMENT '物料规格',
  `unit` VARCHAR(20) COMMENT '单位',
  `quantity` DECIMAL(10,2) NOT NULL COMMENT '入库数量',
  `unit_price` DECIMAL(10,2) COMMENT '单价',
  `amount` DECIMAL(10,2) COMMENT '金额',
  `location_id` BIGINT COMMENT '目标库位ID',
  `location_code` VARCHAR(50) COMMENT '目标库位编码',
  `batch_no` VARCHAR(50) COMMENT '批次号',
  `production_date` DATE COMMENT '生产日期',
  `expiry_date` DATE COMMENT '有效期至',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='其他入库单明细表';

-- 其他出库单表
DROP TABLE IF EXISTS `other_outbound_order`;
CREATE TABLE `other_outbound_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '出库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '出库单号',
  `type` TINYINT NOT NULL COMMENT '出库类型(1-报废出库 2-调拨出库 3-损耗出库 4-转库出库 9-其他)',
  `source_warehouse_id` BIGINT NOT NULL COMMENT '来源仓库ID',
  `source_warehouse_name` VARCHAR(100) COMMENT '来源仓库名称',
  `target_warehouse_id` BIGINT COMMENT '目标仓库ID',
  `target_warehouse_name` VARCHAR(100) COMMENT '目标仓库名称',
  `operator_id` BIGINT COMMENT '经办人ID',
  `operator_name` VARCHAR(50) COMMENT '经办人姓名',
  `approver_id` BIGINT COMMENT '审批人ID',
  `approver_name` VARCHAR(50) COMMENT '审批人姓名',
  `outbound_date` DATE COMMENT '出库日期',
  `status` TINYINT DEFAULT 0 COMMENT '状态(0-待审批 1-已审批 2-已出库 3-已取消)',
  `total_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '总数量',
  `total_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '总金额',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='其他出库单表';

-- 其他出库单明细表
DROP TABLE IF EXISTS `other_outbound_order_detail`;
CREATE TABLE `other_outbound_order_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` BIGINT NOT NULL COMMENT '出库单ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '出库单号',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `material_code` VARCHAR(50) COMMENT '物料编码',
  `material_name` VARCHAR(100) COMMENT '物料名称',
  `specification` VARCHAR(200) COMMENT '物料规格',
  `unit` VARCHAR(20) COMMENT '单位',
  `quantity` DECIMAL(10,2) NOT NULL COMMENT '出库数量',
  `unit_price` DECIMAL(10,2) COMMENT '单价',
  `amount` DECIMAL(10,2) COMMENT '金额',
  `source_location_id` BIGINT COMMENT '来源库位ID',
  `source_location_code` VARCHAR(50) COMMENT '来源库位编码',
  `batch_no` VARCHAR(50) COMMENT '批次号',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='其他出库单明细表';

-- 库存预警表
DROP TABLE IF EXISTS `inventory_alert`;
CREATE TABLE `inventory_alert` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '预警ID',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `material_code` VARCHAR(50) COMMENT '物料编码',
  `material_name` VARCHAR(100) COMMENT '物料名称',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `warehouse_name` VARCHAR(100) COMMENT '仓库名称',
  `current_quantity` DECIMAL(10,2) COMMENT '当前库存数量',
  `min_quantity` DECIMAL(10,2) COMMENT '安全库存下限',
  `max_quantity` DECIMAL(10,2) COMMENT '安全库存上限',
  `alert_type` TINYINT NOT NULL COMMENT '预警类型(1-低于下限 2-高于上限)',
  `alert_status` TINYINT DEFAULT 0 COMMENT '预警状态(0-未处理 1-已处理)',
  `handler_id` BIGINT COMMENT '处理人ID',
  `handler_name` VARCHAR(50) COMMENT '处理人姓名',
  `handle_time` DATETIME COMMENT '处理时间',
  `handle_remark` VARCHAR(500) COMMENT '处理说明',
  `alert_level` TINYINT COMMENT '预警级别(1-一般 2-重要 3-紧急)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  KEY `idx_material_id` (`material_id`),
  KEY `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存预警表';

-- 盘点单表
DROP TABLE IF EXISTS `stock_check_order`;
CREATE TABLE `stock_check_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '盘点单ID',
  `check_no` VARCHAR(50) NOT NULL COMMENT '盘点单号',
  `warehouse_id` BIGINT NOT NULL COMMENT '仓库ID',
  `warehouse_name` VARCHAR(100) COMMENT '仓库名称',
  `check_type` TINYINT NOT NULL COMMENT '盘点类型(1-全面盘点 2-部分盘点 3-动态盘点)',
  `check_status` TINYINT DEFAULT 0 COMMENT '盘点状态(0-待盘点 1-盘点中 2-已完成 3-已作废)',
  `plan_check_date` DATE COMMENT '计划盘点日期',
  `actual_check_date` DATE COMMENT '实际盘点日期',
  `checker_id` BIGINT COMMENT '盘点人ID',
  `checker_name` VARCHAR(50) COMMENT '盘点人姓名',
  `auditor_id` BIGINT COMMENT '审核人ID',
  `auditor_name` VARCHAR(50) COMMENT '审核人姓名',
  `book_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '账面数量',
  `actual_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '实盘数量',
  `diff_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '差异数量',
  `diff_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '差异金额',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_check_no` (`check_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点单表';

-- 盘点单明细表
DROP TABLE IF EXISTS `stock_check_detail`;
CREATE TABLE `stock_check_detail` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '明细ID',
  `order_id` BIGINT NOT NULL COMMENT '盘点单ID',
  `check_no` VARCHAR(50) NOT NULL COMMENT '盘点单号',
  `material_id` BIGINT NOT NULL COMMENT '物料ID',
  `material_code` VARCHAR(50) COMMENT '物料编码',
  `material_name` VARCHAR(100) COMMENT '物料名称',
  `specification` VARCHAR(200) COMMENT '物料规格',
  `unit` VARCHAR(20) COMMENT '单位',
  `location_id` BIGINT COMMENT '库位ID',
  `location_code` VARCHAR(50) COMMENT '库位编码',
  `batch_no` VARCHAR(50) COMMENT '批次号',
  `book_quantity` DECIMAL(10,2) COMMENT '账面数量',
  `actual_quantity` DECIMAL(10,2) COMMENT '实盘数量',
  `diff_quantity` DECIMAL(10,2) DEFAULT 0 COMMENT '差异数量',
  `diff_amount` DECIMAL(10,2) DEFAULT 0 COMMENT '差异金额',
  `diff_type` TINYINT COMMENT '差异类型(1-盘盈 2-盘亏 3-无差异)',
  `handle_status` TINYINT DEFAULT 0 COMMENT '处理状态(0-待处理 1-已处理)',
  `remark` VARCHAR(500) COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` BIGINT COMMENT '创建人ID',
  `update_by` BIGINT COMMENT '更新人ID',
  `deleted` TINYINT DEFAULT 0 COMMENT '删除标记(0-未删除 1-已删除)',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盘点单明细表';
