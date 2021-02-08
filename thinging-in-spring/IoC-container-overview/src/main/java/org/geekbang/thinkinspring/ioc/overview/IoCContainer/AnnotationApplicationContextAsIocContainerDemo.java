package org.geekbang.thinkinspring.ioc.overview.IoCContainer;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName BeanFactoryAsIocContainerDemo
 * @create 2/1/21 5:49 PM
 * @Version 1.0.0
 */
@Configuration
public class AnnotationApplicationContextAsIocContainerDemo {
    
    private static String location = "classpath:/META-INF/dependency.xml";
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //加载配置  将当前类作为配置类
        applicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        lookupCollectionByType(applicationContext);         
    }
    
    @Bean
    public User user(){
        User user = new User();
        user.setAge(16);
        user.setName("cty");
        return user;        
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
}
