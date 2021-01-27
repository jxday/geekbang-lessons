package org.geekbang.thinkinspring.ioc.overview.dependencylookup;

import org.geekbang.thinkinspring.ioc.overview.annotation.Super;
import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName DependencyLookUpDemo
 * @create 1/27/21 1:55 PM
 * @Version 1.0.0
 */
public class DependencyLookUpDemo {
    
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency.xml");
//        也可以写成：
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency.xml");
        
        
//        lookupByName(beanFactory);
        lookupLazy(beanFactory);
        lookupByType(beanFactory);
//        lookupCollectionByType(beanFactory);
//        lookupCollectionByAnnotation(beanFactory);
        
    }

    

    public static void lookupByName(BeanFactory beanFactory){
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }

    /**
     * @MethodName:
     * @Description: 延迟依赖查找主要用于获取 BeanFactory 后，不马上获取相关的 Bean，
     *      比如在 BeanFactoryPostProcessor 接口中获取 ConfigurableListableBeanFactory 时，不马上获取，降低 Bean 过早初始化的情况
     * @Param:
     * @Return:
     * @Author: cty
     * @Date: 1/27/21
     **/
    public static void lookupLazy(BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println(user);

    }

    /**
     * @MethodName: lookupByType
     * @Description:    如果存在多个User类型(包括子类)的实例，就会报错   可以添加primary标签设置某一bean作为类型查找的首选bean
     *      {@NoUniqueBeanDefinitionException}
     * @Param: [beanFactory]
     * @Return: void
     * @Author: cty
     * @Date: 1/27/21
     **/
    private static void lookupByType(BeanFactory beanFactory) {
        User bean = beanFactory.getBean(User.class);
        System.out.println(bean);
    }
    
    public static void lookupCollectionByType(BeanFactory beanFactory){
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.err.println(beans);
            beans.entrySet().forEach(new Consumer<Map.Entry<String, User>>() {
                @Override
                public void accept(Map.Entry<String, User> stringUserEntry) {
                    System.out.println(stringUserEntry.getKey());
                    System.out.println(stringUserEntry.getValue());
                }
            });
        }
    }

    /**
     * @MethodName: lookupCollectionByAnnotation
     * @Description: 根据bean上的注解获取beans
     * @Param: [beanFactory]
     * @Return: void
     * @Author: cty                                                           
     * @Date: 1/27/21
    **/
    private static void lookupCollectionByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            //获取id
            String[] names = listableBeanFactory.getBeanNamesForAnnotation(Super.class);
            Stream.of(names).forEach(a -> System.err.println(a));
            //获取beans
            Map<String, Object> beans = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(beans);
        }
    }
}
