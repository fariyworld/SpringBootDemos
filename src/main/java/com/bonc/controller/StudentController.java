package com.bonc.controller;


import com.bonc.dao.StudentDao;
import com.bonc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: MongoDB
 *
 * Created by mace on 18:00 2018/4/9.
 */
@RestController
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value = "/findByName2MongoDB/{name}")
    public Student findByName2MongoDB(@PathVariable String name){

        System.out.println("前台传过来的参数:"+name);

        return studentDao.findByName(name);
    }

}
