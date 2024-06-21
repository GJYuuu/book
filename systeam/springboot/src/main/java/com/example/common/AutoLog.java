package com.example.common;

import java.lang.annotation.*;

@Target(ElementType.METHOD)//作用于方法
@Retention(RetentionPolicy.RUNTIME)//什么级别保存该注释信息
@Documented
public @interface AutoLog {
    String value() default "";//属性value默认值是空
}
