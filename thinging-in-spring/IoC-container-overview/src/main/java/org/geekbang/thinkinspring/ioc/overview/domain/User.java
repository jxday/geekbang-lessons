package org.geekbang.thinkinspring.ioc.overview.domain;

import org.geekbang.thinkinspring.ioc.overview.annotation.Super;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName User
 * @create 1/27/21 1:55 PM
 * @Version 1.0.0
 */
public class User {
    private Integer age;
    
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
