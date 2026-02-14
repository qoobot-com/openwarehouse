# OpenWarehouse

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.10-brightgreen.svg)](https://spring.io/projects/spring-boot)

> 开源仓储管理系统 | Open Source Warehouse Management System

## 项目简介

OpenWarehouse 是一个基于 **Spring Boot 3.5.10 + Java 21** 开发的现代化仓储管理系统，提供完善的仓库管理、库存管理、出入库管理、盘点管理、报表统计等功能。系统采用前后端分离架构，提供完整的 RESTful API 接口。

### 核心特性

- 🚀 **现代化技术栈**：基于 Spring Boot 3.x 和 Java 21，使用虚拟线程等新特性
- 📦 **完整的仓储管理**：仓库、库区、货架、库位四级管理体系
- 📊 **智能库存管理**：实时库存查询、库存预警、批次管理
- 📈 **丰富的报表功能**：入库明细、出库明细、库存台账等统计报表
- 🔐 **完善的权限体系**：基于 JWT 的认证授权和细粒度权限控制
- 📝 **规范的代码结构**：MVC 三层架构，遵循阿里巴巴开发规范
- 🧪 **单元测试覆盖**：核心业务逻辑单元测试覆盖
- ⚡ **高性能优化**：数据库索引优化、查询优化、缓存策略

## 技术栈

| 分类 | 技术 | 版本 | 说明 |
|------|------|------|------|
| **后端框架** | Spring Boot | 3.5.10 | 核心框架 |
| **开发语言** | Java | 21 | LTS版本，支持虚拟线程 |
| **ORM框架** | MyBatis-Plus | 3.5.7 | 持久层框架 |
| **数据库** | MySQL | 8.4.0 | 关系型数据库 |
| **连接池** | HikariCP | 6.3.3 | 高性能连接池 |
| **接口文档** | SpringDoc | 2.3.0 | OpenAPI 3.0 |
| **JSON处理** | Fastjson2 | 2.0.43 | JSON 序列化 |
| **工具库** | Hutool | 5.8.23 | Java工具类库 |
| **测试框架** | JUnit 5 | 5.10.0 | 单元测试 |

## 模块架构

```
openwarehouse/
├── openwarehouse-common/    # 公共工具模块
│   ├── 统一响应结果 (Result)
│   ├── 全局异常处理 (GlobalExceptionHandler)
│   ├── 基础实体类 (BaseEntity)
│   ├── 跨域配置 (CorsConfig)
│   └── 字段自动填充 (MyMetaObjectHandler)
│
├── openwarehouse-auth/      # 认证授权模块
│   ├── JWT 工具 (JwtUtil)
│   ├── JWT 配置 (JwtConfig)
│   └── 认证过滤器 (JwtAuthenticationFilter)
│
├── openwarehouse-system/    # 系统管理模块
│   ├── 用户管理 (User)
│   ├── 角色管理 (Role)
│   ├── 权限管理 (Permission)
│   └── 菜单管理 (Menu)
│
├── openwarehouse-warehouse/  # 仓储核心模块
│   ├── 仓库管理 (Warehouse)
│   ├── 库区管理 (Zone)
│   ├── 货架管理 (Shelf)
│   ├── 库位管理 (Location)
│   ├── 物料管理 (Material)
│   ├── 物料分类 (MaterialCategory)
│   ├── 供应商管理 (Supplier)
│   └── 客户管理 (Customer)
│
├── openwarehouse-inventory/ # 库存管理模块
│   ├── 库存管理 (Inventory)
│   ├── 入库单 (InboundOrder/OtherInboundOrder)
│   ├── 出库单 (OutboundOrder/OtherOutboundOrder)
│   ├── 库存变动 (InventoryLog)
│   ├── 库存预警 (InventoryAlert)
│   ├── 盘点单 (StockCheckOrder)
│   └── 报表服务 (ReportService)
│
└── openwarehouse-api/        # API接口模块
    ├── 启动类 (OpenWarehouseApplication)
    ├── OpenAPI配置 (OpenApiConfig)
    └── 各模块Controller
```

## 功能特性

### ✅ 已实现功能

#### 系统管理
- [x] 用户管理（增删改查）
- [x] 角色管理（增删改查）
- [x] 权限管理（增删改查）
- [x] 菜单管理（树形结构）
- [x] JWT 认证授权
- [x] 统一异常处理
- [x] 接口文档（Swagger/OpenAPI）

#### 仓储管理
- [x] 仓库信息管理
- [x] 库区管理
- [x] 货架管理
- [x] 库位管理（四级编码）
- [x] 物料管理
- [x] 物料分类管理
- [x] 供应商管理
- [x] 客户管理

#### 库存管理
- [x] 实时库存查询
- [x] 可用库存查询
- [x] 采购入库单
- [x] 销售出库单
- [x] 其他入库单（退货、转库、调拨、盘点）
- [x] 其他出库单（报废、调拨、损耗）
- [x] 库存预警（上下限报警）
- [x] 库存变动记录
- [x] 盘点单管理
- [x] 盘点差异处理

#### 报表统计
- [x] 库存台账报表
- [x] 入库明细报表
- [x] 出库明细报表

### 🚧 规划功能

- [ ] 入库任务分配
- [ ] 出库拣货确认
- [ ] 入库质检管理
- [ ] 仓库可视化展示
- [ ] 物料规格属性管理
- [ ] 物料图片上传
- [ ] 供应商评估体系
- [ ] 客户信用管理
- [ ] 库内移动管理
- [ ] Docker 容器化部署

## 快速开始

### 环境要求

| 环境 | 要求 |
|------|------|
| JDK | 21+ |
| Maven | 3.9+ |
| MySQL | 8.0+ |

### 数据库初始化

```bash
# 1. 创建数据库
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS openwarehouse CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# 2. 执行初始化脚本
mysql -u root -p openwarehouse < docs/sql/01-init-database.sql

# 3. （可选）执行索引优化脚本
mysql -u root -p openwarehouse < docs/sql/02-index-optimization.sql
```

### 配置文件修改

修改 `openwarehouse-api/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/openwarehouse?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

### 启动项目

```bash
# 1. 克隆项目
git clone https://github.com/qoobot-com/openwarehouse.git
cd openwarehouse

# 2. 编译项目
mvn clean install -DskipTests

# 3. 启动项目
cd openwarehouse-api
mvn spring-boot:run

# 或者直接运行启动类
java -jar target/openwarehouse-api-1.0.0.jar
```

### 访问接口文档

启动成功后，访问：

- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/api-docs

### 默认账号

```
用户名: admin
密码: admin123
```

## API 接口

### 系统管理

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 用户登录 | POST | `/api/auth/login` | 用户登录获取Token |
| 用户列表 | GET | `/api/system/user/page` | 分页查询用户 |
| 角色列表 | GET | `/api/system/role/page` | 分页查询角色 |
| 权限列表 | GET | `/api/system/permission/page` | 分页查询权限 |
| 菜单列表 | GET | `/api/system/menu/page` | 分页查询菜单 |

### 仓储管理

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 仓库列表 | GET | `/api/warehouse/warehouse/page` | 分页查询仓库 |
| 物料列表 | GET | `/api/warehouse/material/page` | 分页查询物料 |
| 供应商列表 | GET | `/api/warehouse/supplier/page` | 分页查询供应商 |
| 客户列表 | GET | `/api/warehouse/customer/page` | 分页查询客户 |

### 库存管理

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 库存查询 | GET | `/api/inventory/page` | 分页查询库存 |
| 入库单 | GET | `/api/inventory/inbound/page` | 分页查询入库单 |
| 出库单 | GET | `/api/inventory/outbound/page` | 分页查询出库单 |
| 库存预警 | GET | `/api/inventory/alert/page` | 分页查询预警 |
| 盘点单 | GET | `/api/inventory/stock-check/page` | 分页查询盘点单 |

### 报表统计

| 接口 | 方法 | 路径 | 说明 |
|------|------|------|------|
| 库存台账 | GET | `/api/inventory/report/inventory` | 库存台账报表 |
| 入库明细 | GET | `/api/inventory/report/inbound` | 入库明细报表 |
| 出库明细 | GET | `/api/inventory/report/outbound` | 出库明细报表 |

## 开发指南

### 代码规范

项目遵循阿里巴巴 Java 开发规范，主要规范如下：

1. **命名规范**
   - 类名：大驼峰命名（PascalCase）
   - 方法名/变量名：小驼峰命名（camelCase）
   - 常量：全大写下划线分隔（UPPER_SNAKE_CASE）

2. **注释规范**
   - 类注释：使用 JavaDoc，说明类的作用
   - 方法注释：使用 JavaDoc，说明方法的功能、参数、返回值
   - 重要逻辑：使用行内注释说明

3. **分层规范**
   - Controller 层：接收请求、参数校验、返回响应
   - Service 层：业务逻辑处理
   - Mapper 层：数据访问

### 单元测试

```bash
# 运行所有测试
mvn test

# 运行指定测试类
mvn test -Dtest=InventoryServiceTest

# 查看测试覆盖率
mvn jacoco:report
```

### 性能优化

详细优化指南请参考 [性能优化指南](docs/03-性能优化指南.md)

```sql
-- 查看慢查询
SHOW VARIABLES LIKE 'slow_query%';

-- 分析查询计划
EXPLAIN SELECT * FROM inventory WHERE warehouse_id = 1;
```

## 项目文档

- [开发计划进度](docs/01-开发计划进度.md) - 详细的12周开发计划
- [开发进度跟踪](docs/02-开发进度跟踪.md) - 实时开发进度跟踪
- [数据库初始化脚本](docs/sql/01-init-database.sql) - 数据库建表脚本
- [索引优化脚本](docs/sql/02-index-optimization.sql) - 数据库索引优化
- [性能优化指南](docs/03-性能优化指南.md) - 性能优化完整指南

## 开发进度

| 阶段 | 内容 | 状态 |
|------|------|------|
| 第一阶段 | 项目基础搭建 | ✅ 完成 |
| 第二阶段 | 系统管理模块 | ✅ 完成 |
| 第三阶段 | 仓储核心模块 | ✅ 完成 |
| 第四阶段 | 库存管理模块 | ✅ 完成 |
| 第五阶段 | 高级功能与优化 | 🚧 进行中 (60%) |

详细进度请查看 [开发进度跟踪](docs/02-开发进度跟踪.md)

## 数据库设计

系统共设计 **24 张核心表**：

- **系统管理** (7张): sys_user, sys_role, sys_permission, sys_menu, sys_user_role, sys_role_menu, sys_role_permission
- **认证授权** (1张): auth_token
- **仓储管理** (8张): wh_warehouse, wh_zone, wh_shelf, wh_location, material, material_category, supplier, customer
- **库存管理** (8张): inventory, inbound_order, inbound_order_detail, outbound_order, outbound_order_detail, inventory_log, stock_check_order, stock_check_detail

## 常见问题

### 1. 启动失败，提示数据库连接错误

检查 `application.yml` 中的数据库配置是否正确，确保 MySQL 服务已启动。

### 2. 接口文档访问 404

检查启动日志中是否正确扫描到 Controller，确保包路径配置正确。

### 3. JWT Token 过期

默认 Token 有效期为 24 小时，可在 `JwtConfig` 中修改。

### 4. 查询性能慢

执行 `docs/sql/02-index-optimization.sql` 中的索引优化脚本。

## 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 提交 Pull Request

## 开源协议

本项目采用 [MIT License](LICENSE) 开源协议。

## 联系方式

- 🌐 **项目地址**: https://github.com/qoobot-com/openwarehouse
- 📧 **问题反馈**: https://github.com/qoobot-com/openwarehouse/issues
- 📮 **邮箱**: dev@qoobot.com

## 致谢

感谢所有贡献者的支持！

---

<div align="center">

**如果这个项目对您有帮助，请给我们一个 Star ⭐**

Made with ❤️ by OpenWarehouse Team

</div>

