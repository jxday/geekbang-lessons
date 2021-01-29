package org.geekbang.thinkinspring.ioc.overview.dependencyInject;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.geekbang.thinkinspring.ioc.overview.properties.UserProperties;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 〈依赖注入的来源和依赖查找的来源，并不是同一个地方〉
 *       依赖注入和依赖查找的对象来源：
 *          自定义bean
 *          容器内建依赖
 *          容器内建bean对象
 *          
 *          
 *  AbstractApplicationContext实现了ConfigurableApplicationContext接口。
 *
 *  AbstractApplicationContext.getBean()调用了ConfigurableApplicationContext.getBeanFactory().getBean().
 *
 *  ConfigurableApplicationContext.getBeanFactory()返回的就是DefaultListableBeanFactory。也就是依赖注入的BeanFactory对象。          
 *
 * @author cty
 * @ClassName DependenctInjectDemo
 * @create 1/27/21 4:59 PM
 * @Version 1.0.0
 */
public class DependenctInjectDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-inject.xml");
        
        //依赖来源1：    自定义bean
        UserProperties users = beanFactory.getBean("users", UserProperties.class);
        System.out.println("1:" + users);

        
        //验证了：依赖查找的来源和依赖注入的来源并不是同一个地方
        //依赖注入  该bean是一个内建依赖       DefaultListableBeanFactory
        //依赖来源2：    内建依赖
        System.out.println("2:" + users.getBeanFactory());
        
        //依赖查找  抛出异常，找不到该类       因为这个是内建依赖，不在beanFactory容器中
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        
        
        //false
        System.err.println(users.getBeanFactory() == beanFactory);


        System.out.println("3:" + users.getUserObjectFactory().getObject());
        
        
        //todo     
        
        // 是ClassPathXmlApplicationContext
        System.err.println(users.getObjectFactory().getObject());
        //true
        System.err.println(users.getObjectFactory().getObject() == beanFactory);
        
        //依赖来源3：    容器内建  bean
        Environment environment = beanFactory.getBean(Environment.class);
        //StandardEnvironment {
        //          activeProfiles=[], 
        //          defaultProfiles=[default], 
        //          propertySources=
        //              [PropertiesPropertySource {name='systemProperties'}, SystemEnvironmentPropertySource {name='systemEnvironment'}]
        //          }
        System.out.println(environment);

        whoIsIoCContainer(users,beanFactory);
        
    }    
    
    /**
     * @MethodName: whoIsIoCContainer
     * @Description: TODO
     * @Param: [users, beanFactory]     内建依赖所在实体类，new 的 BeanFactory
     * @Return: void
     * @Author: cty
     * @Date: 1/27/21
    **/
    public static void whoIsIoCContainer(UserProperties users,BeanFactory beanFactory){
        
        //ClassPathXmlApplicationContext    ->      AbstractApplicationContext     ->|   ConfigurableApplicationContext     ->  BeanFactory
        //ConfigurableApplicationContext#getBeanFactory()   返回的就是DefaultListableBeanFactory，依赖注入的BeanFactory
        //AbstractApplicationContext#getBean()   return getBeanFactory().getBean();
        
        //为什么表达式为false
        System.err.println(users.getBeanFactory() == beanFactory);
        
        
    }
}
