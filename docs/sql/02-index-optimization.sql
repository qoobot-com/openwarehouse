-- ========================================
-- OpenWarehouse 索引优化脚本
-- ========================================

USE `openwarehouse`;

-- ========================================
-- 系统管理模块索引优化
-- ========================================

-- 用户表索引
CREATE INDEX idx_sys_user_phone ON sys_user(phone);
CREATE INDEX idx_sys_user_email ON sys_user(email);
CREATE INDEX idx_sys_user_status ON sys_user(status);

-- 菜单表索引
CREATE INDEX idx_sys_menu_parent_id ON sys_menu(parent_id);
CREATE INDEX idx_sys_menu_type ON sys_menu(menu_type);

-- 角色菜单关联表索引
CREATE INDEX idx_sys_role_menu_menu_id ON sys_role_menu(menu_id);

-- 角色权限关联表索引
CREATE INDEX idx_sys_role_permission_permission_id ON sys_role_permission(permission_id);

-- ========================================
-- 仓储管理模块索引优化
-- ========================================

-- 仓库表索引
CREATE INDEX idx_wh_warehouse_type ON wh_warehouse(warehouse_type);
CREATE INDEX idx_wh_warehouse_status ON wh_warehouse(status);

-- 库区表索引
CREATE INDEX idx_wh_zone_type ON wh_zone(zone_type);
CREATE INDEX idx_wh_zone_status ON wh_zone(status);

-- 货架表索引
CREATE INDEX idx_wh_shelf_type ON wh_shelf(shelf_type);
CREATE INDEX idx_wh_shelf_status ON wh_shelf(status);

-- 库位表索引
CREATE INDEX idx_wh_location_type ON wh_location(location_type);
CREATE INDEX idx_wh_location_status ON wh_location(status);

-- ========================================
-- 货品管理模块索引优化
-- ========================================

-- 物料表索引
CREATE INDEX idx_material_name ON material(material_name);
CREATE INDEX idx_material_status ON material(status);
CREATE INDEX idx_material_category ON material(category_id);

-- 物料分类表索引
CREATE INDEX idx_material_category_parent_id ON material_category(parent_id);
CREATE INDEX idx_material_category_status ON material_category(status);

-- ========================================
-- 供应商客户管理索引优化
-- ========================================

-- 供应商表索引
CREATE INDEX idx_supplier_name ON supplier(supplier_name);
CREATE INDEX idx_supplier_status ON supplier(status);

-- 客户表索引
CREATE INDEX idx_customer_name ON customer(customer_name);
CREATE INDEX idx_customer_type ON customer(customer_type);
CREATE INDEX idx_customer_status ON customer(status);

-- ========================================
-- 库存管理模块索引优化
-- ========================================

-- 库存表索引
CREATE INDEX idx_inventory_material_warehouse ON inventory(material_id, warehouse_id);
CREATE INDEX idx_inventory_location ON inventory(location_id);

-- 入库单表索引
CREATE INDEX idx_inbound_order_type ON inbound_order(order_type);
CREATE INDEX idx_inbound_order_status ON inbound_order(status);
CREATE INDEX idx_inbound_order_date ON inbound_order(expect_date, actual_date);

-- 入库单明细表索引
CREATE INDEX idx_inbound_detail_material ON inbound_order_detail(material_id);
CREATE INDEX idx_inbound_detail_location ON inbound_order_detail(location_id);
CREATE INDEX idx_inbound_detail_batch ON inbound_order_detail(batch_no);

-- 出库单表索引
CREATE INDEX idx_outbound_order_type ON outbound_order(order_type);
CREATE INDEX idx_outbound_order_status ON outbound_order(status);
CREATE INDEX idx_outbound_order_date ON outbound_order(expect_date, actual_date);

-- 出库单明细表索引
CREATE INDEX idx_outbound_detail_material ON outbound_order_detail(material_id);
CREATE INDEX idx_outbound_detail_location ON outbound_order_detail(location_id);
CREATE INDEX idx_outbound_detail_batch ON outbound_order_detail(batch_no);

-- 库存变动记录表索引
CREATE INDEX idx_inventory_log_material ON inventory_log(material_id);
CREATE INDEX idx_inventory_log_warehouse ON inventory_log(warehouse_id);
CREATE INDEX idx_inventory_log_order_no ON inventory_log(order_no);
CREATE INDEX idx_inventory_log_type ON inventory_log(order_type, change_type);
CREATE INDEX idx_inventory_log_create_time ON inventory_log(create_time);

-- 其他入库单表索引
CREATE INDEX idx_other_inbound_type ON other_inbound_order(type);
CREATE INDEX idx_other_inbound_status ON other_inbound_order(status);
CREATE INDEX idx_other_inbound_date ON other_inbound_order(inbound_date);

-- 其他出库单表索引
CREATE INDEX idx_other_outbound_type ON other_outbound_order(type);
CREATE INDEX idx_other_outbound_status ON other_outbound_order(status);
CREATE INDEX idx_other_outbound_date ON other_outbound_order(outbound_date);

-- 库存预警表索引
CREATE INDEX idx_inventory_alert_material ON inventory_alert(material_id);
CREATE INDEX idx_inventory_alert_warehouse ON inventory_alert(warehouse_id);
CREATE INDEX idx_inventory_alert_type ON inventory_alert(alert_type);
CREATE INDEX idx_inventory_alert_status ON inventory_alert(alert_status);

-- 盘点单表索引
CREATE INDEX idx_stock_check_type ON stock_check_order(check_type);
CREATE INDEX idx_stock_check_status ON stock_check_order(check_status);
CREATE INDEX idx_stock_check_date ON stock_check_order(plan_check_date, actual_check_date);

-- 盘点单明细表索引
CREATE INDEX idx_stock_check_detail_material ON stock_check_detail(material_id);
CREATE INDEX idx_stock_check_detail_order ON stock_check_detail(order_id);

-- ========================================
-- 复合索引优化
-- ========================================

-- 库存查询复合索引
CREATE INDEX idx_inventory_query ON inventory(warehouse_id, material_id, location_id);

-- 入库单查询复合索引
CREATE INDEX idx_inbound_query ON inbound_order(warehouse_id, order_type, status);

-- 出库单查询复合索引
CREATE INDEX idx_outbound_query ON outbound_order(warehouse_id, order_type, status);

-- 库存变动记录查询复合索引
CREATE INDEX idx_inventory_log_query ON inventory_log(warehouse_id, material_id, create_time);

-- 库存预警查询复合索引
CREATE INDEX idx_inventory_alert_query ON inventory_alert(warehouse_id, alert_status, alert_level);

-- ========================================
-- 全文索引（可选，用于模糊搜索）
-- ========================================

-- 物料名称全文索引
-- ALTER TABLE material ADD FULLTEXT INDEX ft_material_name (material_name);

-- 物料规格全文索引
-- ALTER TABLE material ADD FULLTEXT INDEX ft_material_spec (spec);

-- ========================================
-- 分析表结构
-- ========================================

-- 查看表索引信息
-- SHOW INDEX FROM inventory;
-- SHOW INDEX FROM inbound_order;
-- SHOW INDEX FROM outbound_order;

-- ========================================
-- 性能监控
-- ========================================

-- 查看慢查询日志
-- SHOW VARIABLES LIKE 'slow_query%';
-- SHOW VARIABLES LIKE 'long_query_time';

-- 查看索引使用情况
-- SELECT * FROM sys.schema_unused_indexes WHERE object_schema = 'openwarehouse';
