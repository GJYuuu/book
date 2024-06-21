package com.example.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        //指定controller统一接口前缀,相当于在url上拼了一个/api
        configurer.addPathPrefix("/api",clazz->clazz.isAnnotationPresent(RestController.class));
    }

    //加自定义拦截器JwtInterceptor 设置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/api/admin/register")//拦截所有/api开头的,但是要排除掉登录和注册
                .excludePathPatterns("/api/files/**")//放行文件上传和下载
                .excludePathPatterns("/api/type/upload")
                .excludePathPatterns("/api/captcha");

    }
}
