package com.twinswolves.web.admin.custom.config;

import com.twinswolves.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import com.twinswolves.web.admin.custom.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfiguration 类用于对 Spring MVC 进行自定义配置，
 * 通过实现 WebMvcConfigurer 接口，注册类型转换器工厂和拦截器。
 * 该类会在 Spring 应用启动时被加载，对特定请求路径进行拦截处理，
 * 同时配置字符串到枚举类型的转换逻辑。
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 自动注入 StringToBaseEnumConverterFactory 实例。
     * 该工厂用于将字符串转换为自定义的基础枚举类型，
     * 在处理请求参数时，可将字符串自动转换为对应的枚举值。
     */
    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    /**
     * 自动注入 AuthenticationInterceptor 拦截器实例。
     * 该拦截器用于处理请求的认证逻辑，
     * 确保只有通过认证的用户才能访问受保护的管理后台资源。
     */
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    /**
     * 重写 addFormatters 方法，向 FormatterRegistry 中添加类型转换器工厂。
     * 这样在 Spring MVC 处理请求参数时，就会使用该工厂进行字符串到枚举类型的转换。
     *
     * @param registry 格式化器注册器，用于注册转换器、格式化器等。
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 向注册器中添加字符串到基础枚举类型的转换器工厂
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }

    /**
     * 重写 addInterceptors 方法，向 InterceptorRegistry 中注册拦截器并配置拦截规则。
     *
     * @param registry 拦截器注册器，用于添加和配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 向注册器中添加 AuthenticationInterceptor 拦截器
        // 配置该拦截器拦截所有以 /admin/ 开头的请求路径
        // 同时排除所有以 /admin/login/ 开头的请求路径，即登录相关接口不进行拦截
        registry.addInterceptor(this.authenticationInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/login/**",
                        "/doc.html",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**"
                );
    }
}
