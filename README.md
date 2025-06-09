# Java基于SpringBoot封装的脚手架技术文档

---

## 📌 一、项目概述

本项目是基于 **Spring Boot 3.0** 的企业级后台管理系统，采用模块化架构设计，包含通用工具模块、数据模型模块及前后端分离的WEB服务模块。系统集成JWT认证、MyBatis-Plus快速开发、Redis缓存及Knife4j接口文档，适用于中大型企业级应用的后台管理场景。

---

## 🛠️ 二、技术栈详情

| 技术组件          | 版本    | 说明                                                                 |
|-------------------|---------|----------------------------------------------------------------------|
| **Spring Boot**   | 3.0.5   | 核心框架，提供自动配置、依赖管理及快速开发支持                       |
| **MyBatis-Plus**  | 3.5.3   | 持久层增强工具，简化CRUD操作，支持逻辑删除、自动填充等特性           |
| **Spring Security**| 6.1.0  | 安全框架，结合JWT实现接口权限控制                                    |
| **Knife4j**       | 4.1.0   | Swagger增强工具，提供可视化API文档及测试功能                         |
| **Redis**         | 6.2     | 内存数据库，用于缓存用户会话、令牌黑名单及高频访问数据                |
| **Maven**         | 3.8.6   | 依赖管理工具，父工程统一管理所有子模块依赖版本                       |
| **Flyway**        | 9.11.0  | 数据库迁移工具，确保环境数据库结构一致性                             |

---

## 📦 三、模块详细说明

### 3.1 通用模块（common）

**职责**：提供全局通用功能，包括工具类、统一响应封装、第三方组件配置等。

#### 核心目录结构

```text
common/
├── src/main/java/
│   ├── com.example.common.util/       # 工具类（JwtUtil/RedisUtil/SmsUtil）
│   ├── com.example.common.config/     # 配置类（RedisConfiguration/MyBatisPlusConfig）
│   └── com.example.common.domain/     # 领域模型（Result/ResultCodeEnum）
└── src/main/resources/
    ├── application-common.yml         # 通用配置文件
    └── logback-spring.xml             # 日志配置文件
```

### 3.2 数据模型模块（model）

**职责**：定义数据库实体类及MyBatis映射关系。

#### 核心功能点

- `com.example.model.entity`：数据库实体类（如`User`/`Role`/`Menu`），使用`@TableName`/`@TableField`注解映射表结构
- `com.example.model.mapper`：继承`BaseMapper`的接口类（如`UserMapper`），自动生成CRUD方法
- `src/main/resources/mapper`：XML格式自定义SQL（复杂查询场景）

### 3.3 WEB模块（web）

#### 3.3.1 后台管理系统（admin）

**职责**：提供后台管理端接口（用户管理/角色权限/日志监控）。

#### 关键组件

| 包路径                      | 功能说明                                   |
|-----------------------------|-------------------------------------------|
| `com.example.admin.controller` | 用户/角色/日志等功能的HTTP接口层          |
| `com.example.admin.service`   | 业务逻辑实现层（密码加密/权限分配）        |
| `src/main/resources/mapper/admin` | 后台管理相关自定义SQL文件                 |
| `application-admin.yml`       | 服务端口（8555）、JWT密钥、Redis连接配置   |

#### 3.3.2 前端接口服务（api）

**职责**：提供前端应用调用的业务接口（商品查询/订单提交）。

#### 特色实现

- `GoodsController`：商品列表查询（带Redis缓存）、详情接口
- `OrderController`：订单提交（事务控制）、支付回调处理
- `application-api.yml`：接口限流（Sentinel）、短信服务AK/SK配置

---

## 🚀 四、核心功能实现细节

### 4.1 MyBatis-Plus增强配置

```java
// MyBatisPlusConfig.java
@Configuration
public class MyBatisPlusConfig {
    // 分页插件配置
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    // 自动填充配置（创建时间自动赋值）
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
            }
        };
    }
}
```

### 4.2 JWT认证流程详解

1. **登录验证**：用户提交账号密码 → `UserDetailsService`查询用户信息 → 密码匹配后生成双token
2. **token生成**：
   - `access_token`（15分钟有效期）：用于接口访问
   - `refresh_token`（7天有效期）：用于刷新access_token
3. **拦截校验**：`JwtAuthenticationFilter`从请求头提取token → `JwtUtil`校验签名和过期时间 → 解析用户信息存入SecurityContext
4. **登出处理**：将token加入Redis黑名单（与token剩余有效期同步过期）

---

## ⚡ 五、快速启动指南

### 5.1 环境准备

| 环境组件       | 版本要求       | 说明                          |
|----------------|----------------|-------------------------------|
| JDK            | 17+（推荐OpenJDK） | 必须安装17及以上版本          |
| MySQL          | 8.0+           | 提前创建数据库`api_db`（utf8mb4）|
| Redis          | 6.2+           | 默认端口6379（无密码）        |
| Maven          | 3.8+           | 配置阿里云镜像加速下载依赖    |

### 5.2 初始化数据库

```bash
# 在父工程目录执行，自动执行flyway迁移
mvn flyway:migrate -Dflyway.url=jdbc:mysql://localhost:3306/api_db -Dflyway.user=root -Dflyway.password=123456
```

### 5.3 启动服务

```bash
# 启动后台管理系统（端口8555）
cd web/admin && mvn spring-boot:run

# 启动前端接口服务（端口8666）
cd ../api && mvn spring-boot:run
```

### 5.4 验证服务

- 后台API文档：`http://localhost:8555/doc.html` 🌟
- 前端接口文档：`http://localhost:8666/doc.html` 🌟

---

## 📝 六、接口规范

### 6.1 统一响应格式

```json
{
  "code": 200,          // 业务状态码（200成功，400参数错误，401未认证，500服务器错误）
  "message": "操作成功", // 提示信息
  "data": {
    "id": 1,
    "username": "admin"
  },
  "timestamp": 1712345678900 // 响应时间戳
}
```

### 6.2 错误码说明

| 错误码 | 说明                 | 示例场景                     |
|--------|----------------------|------------------------------|
| 200    | ✅ 成功              | 正常接口调用                 |
| 400    | ⚠️ 参数校验失败      | 手机号格式错误               |
| 401    | 🔒 token无效/过期    | access_token过期未刷新       |
| 403    | ❌ 无访问权限        | 普通用户尝试访问管理员接口   |
| 500    | 🛑 服务器内部错误     | 数据库连接失败               |

---

## ⚠️ 七、注意事项

### 7.1 配置管理

生产环境需在`application-prod.yml`中补充：

- `mybatis-plus.configuration.log-impl: null`（关闭SQL日志）
- `spring.redis.password: your_redis_password`（配置Redis密码）
- `jwt.secret: your_32bit_random_string`（设置高强度JWT密钥）

### 7.2 性能优化

- 高频查询接口（如商品列表）添加Redis缓存（设置合理过期时间）
- 批量操作（如用户导入）使用`MyBatis-Plus.saveBatch`（默认100条/批）

### 7.3 安全加固

- 接口权限控制：`@PreAuthorize("hasRole('ADMIN')")`（限制管理员访问）
- 敏感信息存储：用户密码使用BCrypt加密（盐值自动生成）

### 7.4 监控报警

- 集成`Spring Boot Actuator`，暴露`/health`/`/metrics`监控端点
- 配置`Prometheus+Grafana`监控QPS、数据库连接数等核心指标
