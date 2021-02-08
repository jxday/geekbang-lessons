package org.geekbang.thinkinspring.beanDefinition;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName BeanDefinitionCreationDemo
 * @create 2/5/21 4:14 PM
 * @Version 1.0.0
 */
public class BeanDefinitionCreationDemo {


    public static void main(String[] args) {
        //1。通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置    返回builder
        builder.addPropertyValue("name","cty");
        builder.addPropertyValue("age",16);
        //获取BeanDefinition 实例
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        // beanDefinition 并非 Bean 最终状态，还可以自定义修改


        //2。通过AbstractBeanDefinition 以及派生类        
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置 Bean 类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过 MutablePropertyValues 批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        //返回 void
        propertyValues.addPropertyValue("name","cty");
        //返回 MutablePropertyValues
        propertyValues
                .add("age",18)
                .add("name","ldd");
        genericBeanDefinition.setPropertyValues(propertyValues);
        
        
    }
    
    
    
}
