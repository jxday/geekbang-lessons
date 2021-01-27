package org.geekbang.thinkinspring.ioc.overview.properties;

import org.geekbang.thinkinspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.applet.AppletContext;
import java.util.Collection;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName UserProperties
 * @create 1/27/21 5:00 PM
 * @Version 1.0.0
 */
public class UserProperties {
    
    private Collection<User> users;     //自定义bean
    
    private BeanFactory beanFactory;    //内建依赖 非bean对象
    
    private ObjectFactory<User> userObjectFactory;
    
    private ObjectFactory<ApplicationContext> objectFactory;

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserProperties{" +
                "users=" + users +
                ", beanFactory=" + beanFactory +
                '}';
    }
}
