package org.geekbang.thinkinspring.ioc.overview.domain;

import org.geekbang.thinkinspring.ioc.overview.annotation.Super;

/**
 * 〈〉
 *
 * @author cty
 * @ClassName SuperUser
 * @create 1/27/21 2:40 PM
 * @Version 1.0.0
 */
@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
