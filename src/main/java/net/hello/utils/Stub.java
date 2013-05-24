package net.hello.utils;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Created with IntelliJ IDEA.
 * User: Ayesha
 * Date: 23/05/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedResource
public class Stub {

    private String name;
    private String age;

    public void setName(String name) {
        this.name = name;
    }

    @ManagedOperation
    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @ManagedOperation
    public String getAge() {
        return age;
    }
}
