package org.geekbang.thinkinspring.ioc.overview.IoCContainer;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName AnnotationBeanDefinitionDemo
 * @create 2/7/21 5:44 PM
 * @Version 1.0.0
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册 Configuration Class 配置类
        context.register(AnnotationApplicationContextAsIocContainerDemo.class);
        
        
        //通过 BeanDefiniton 注册API 实现
        registerUserBeanDefinition(context,"yyds");
        registerUserBeanDefinition(context);
        
        
        //启动容器
        context.refresh();
        //按照类型查找
//        System.out.println(context.getBeansOfType(AnnotationBeanDefinitionDemo.class));
        System.err.println("aa:"+context.getBeansOfType(Config.class));
        System.out.println(context.getBeansOfType(User.class));
        //显式地关闭容器
        context.close();

    }
    
    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("age",18)
                .addPropertyValue("name","ldd");
        //注册 BeanDefinition
        if (StringUtils.isEmpty(beanName)){
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }else {
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }
        
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry,null);
    }    
    
    @Component
    public static class Config{
        
        @Bean
        public User user(){
            User user = new User();
            user.setAge(26);
            user.setName("cty");
            return user;
        }
        
    }
}
