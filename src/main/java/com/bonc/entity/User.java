package com.bonc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: redis
 *
 * Created by mace on 15:34 2018/4/10.
 */
@Setter
@Getter
@ToString
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

}
