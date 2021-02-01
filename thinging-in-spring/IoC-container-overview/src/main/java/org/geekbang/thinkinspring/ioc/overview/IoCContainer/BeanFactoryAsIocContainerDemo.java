package org.geekbang.thinkinspring.ioc.overview.IoCContainer;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

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
public class BeanFactoryAsIocContainerDemo {
    
    private static String location = "classpath:/META-INF/dependency.xml";
    
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载配置
        int definitions = reader.loadBeanDefinitions(location);
        System.out.println(definitions);

        lookupCollectionByType(beanFactory);
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
