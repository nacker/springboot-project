package com.twinswolves.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类，用于配置 MyBatis-Plus 的相关功能，如分页插件等。
 */
@Configuration
// 扫描指定包下的 Mapper 接口，将其注册到 Spring 容器中。但当前包路径可能有误，需检查 com.twinswolves.friends.web.*.mapper 是否存在
@MapperScan("com.twinswolves.web.*.mapper")
public class MybatisPlusConfiguration {

    /**
     * 配置 MyBatis-Plus 分页插件。
     * 该方法会创建一个 MyBatis-Plus 拦截器实例，并添加分页拦截器，用于实现数据库查询的分页功能。
     *
     * @return MyBatis-Plus 拦截器实例，包含分页拦截器。
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建 MyBatis-Plus 拦截器实例
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 向拦截器中添加分页拦截器，指定数据库类型为 MySQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

//    /**
//     * 配置 MyBatis-Plus 分页插件，包含更详细的分页配置。
//     * 该方法会创建一个 MyBatis-Plus 拦截器实例，并添加分页拦截器，支持溢出处理和设置最大单页限制数量。
//     *
//     * @return MyBatis-Plus 拦截器实例，包含配置后的分页拦截器。
//     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        // 创建 MyBatis-Plus 拦截器实例
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//
//        // 创建分页拦截器实例，指定数据库类型为 MySQL
//        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
//        // 设置溢出处理，当页码超出范围时，自动调整到有效页码
//        paginationInterceptor.setOverflow(true);
//        // 设置最大单页限制数量，防止一次性查询过多数据
//        paginationInterceptor.setMaxLimit(100L);
//
//        // 向拦截器中添加分页拦截器
//        interceptor.addInnerInterceptor(paginationInterceptor);
//        return interceptor;
//    }
//
//    /**
//     * 配置 MyBatis 的下划线转驼峰命名规则。
//     * 该方法会返回一个 ConfigurationCustomizer 实例，用于将数据库表字段的下划线命名自动转换为 Java 对象的驼峰命名。
//     *
//     * @return ConfigurationCustomizer 实例，用于自定义 MyBatis 配置。
//     */
//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setMapUnderscoreToCamelCase(true);
//    }
}