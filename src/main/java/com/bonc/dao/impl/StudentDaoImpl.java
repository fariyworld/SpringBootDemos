package com.bonc.dao.impl;

import com.bonc.dao.StudentDao;
import com.bonc.entity.Student;
import com.bonc.util.MongoOpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * @Description: Created by mace on 18:20 2018/4/9.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Student findByName(String name) {

        Query query=new Query(Criteria.where("name").is(name));

        Student student = mongoTemplate.findOne(query, Student.class, "students");

        student.setDate(MongoOpsUtil.id2time(student.getId()));

        return student;
    }
}
