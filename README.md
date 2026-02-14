# OpenWarehouse

开源仓储管理系统 (Open Source Warehouse Management System)

## 项目简介

OpenWarehouse 是一个基于 Spring Boot 3.x + Java 21 开发的现代化仓储管理系统，提供完善的仓库管理、库存管理、出入库管理等功能。

## 技术栈

- **后端框架**: Spring Boot 3.5.10
- **开发语言**: Java 21
- **ORM框架**: MyBatis-Plus 3.5.7
- **数据库**: MySQL 8.4.0
- **连接池**: HikariCP 6.3.3
- **接口文档**: SpringDoc (OpenAPI 3.0)
- **工具库**: Hutool 5.8.23, Apache Commons

## 模块说明

- **openwarehouse-common**: 公共工具模块，提供统一的响应结构、异常处理、工具类等
- **openwarehouse-auth**: 认证授权模块，基于JWT实现用户认证和权限控制
- **openwarehouse-system**: 系统管理模块，提供用户、角色、权限、菜单等基础功能
- **openwarehouse-warehouse**: 仓储核心模块，提供仓库、库区、货架、库位、货品等管理
- **openwarehouse-inventory**: 库存管理模块，提供入库、出库、库存查询等功能
- **openwarehouse-api**: API接口模块，提供RESTful API接口和启动类

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.9+
- MySQL 8.0+

### 数据库初始化

```bash
# 执行数据库初始化脚本
mysql -u root -p < docs/sql/01-init-database.sql
```

### 启动项目

```bash
# 克隆项目
git clone https://github.com/qoobot-com/openwarehouse.git

# 进入项目目录
cd openwarehouse

# 编译项目
mvn clean install

# 启动项目
cd openwarehouse-api
mvn spring-boot:run
```

### 访问接口文档

启动成功后，访问接口文档：http://localhost:8080/api/swagger-ui.html

## 项目结构

```
openwarehouse/
├── openwarehouse-common/    # 公共工具模块
├── openwarehouse-auth/      # 认证授权模块
├── openwarehouse-system/    # 系统管理模块
├── openwarehouse-warehouse/  # 仓储核心模块
├── openwarehouse-inventory/ # 库存管理模块
├── openwarehouse-api/        # API接口模块
├── docs/                    # 文档目录
│   ├── 01-开发计划进度.md   # 开发计划
│   └── sql/                 # SQL脚本
├── pom.xml                  # 父POM文件
└── README.md                # 项目说明
```

## 开发计划

详细的开发计划请参考 [开发计划进度.md](docs/01-开发计划进度.md)

## 功能特性

### 已实现功能

- [x] 项目基础架构搭建
- [x] 多模块Maven项目配置
- [x] 统一响应结果封装
- [x] 全局异常处理
- [x] 数据库表结构设计
- [x] 接口文档集成

### 规划功能

- [ ] 用户认证与授权
- [ ] 系统管理(用户、角色、权限、菜单)
- [ ] 仓库管理(仓库、库区、货架、库位)
- [ ] 货品管理(物料、分类、供应商)
- [ ] 入库管理(采购入库、其他入库)
- [ ] 出库管理(销售出库、其他出库)
- [ ] 库存管理(库存查询、库存预警)
- [ ] 盘点管理
- [ ] 报表统计

## 默认账号

- 用户名: admin
- 密码: admin123

## 开源协议

本项目采用 MIT 协议开源。

## 联系方式

- 项目地址: https://github.com/qoobot-com/openwarehouse
- 问题反馈: https://github.com/qoobot-com/openwarehouse/issues
- 邮箱: dev@qoobot.com

## License

[MIT License](LICENSE)

