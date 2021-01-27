package org.geekbang.thinkinspring.ioc.overview.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @MethodName: 
 * @Description: 
 * @Param: 
 * @Return: 
 * @Author: cty
 * @Date: 1/27/21
**/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {
    
}
