package com.bonc.service.impl;

import com.bonc.entity.Users;
import com.bonc.mapper_solr.UsersMapper;
import com.bonc.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: Created by mace on 21:14 2018/4/23.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUsersById(Integer id) {

        return usersMapper.getUserById(id);
    }

    //事务管理
    @Transactional
    @Override
    public String insertUsers(Users user) {

        if(usersMapper.insertUsers(user)>0)
            return "success";
        else
            return "failed";
    }
}
