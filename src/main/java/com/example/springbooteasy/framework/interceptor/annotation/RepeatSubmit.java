package com.example.springbooteasy.framework.interceptor.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解防止表单重复提交
 * 
 * @author xiaorui
 *
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit
{

}