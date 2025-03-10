package com.jxau.li.aspect;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})  // 支持类和方法上
@Retention(RetentionPolicy.RUNTIME)  // 注解在运行时生效
@Documented
public @interface LogOperation {
    /**
     * 操作类型描述（如：新增用户、删除订单）
     */
    String type() default "UNKNOWN";
}