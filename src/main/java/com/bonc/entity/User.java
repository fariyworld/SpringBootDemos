package com.bonc.entity;

import java.io.Serializable;

/**
 * @Description: redis
 *
 * Created by mace on 15:34 2018/4/10.
 */
public class User implements Serializable {

    //private static final long serialVersionUID = -1L;
    private static final long serialVersionUID = 2087521549338416984L;

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
