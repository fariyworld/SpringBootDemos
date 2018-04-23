package com.bonc.dao;

import com.bonc.entity.Student;


/**
 * @Description: Created by mace on 17:58 2018/4/9.
 */
public interface StudentDao {

    Student findByName(String name);
}
