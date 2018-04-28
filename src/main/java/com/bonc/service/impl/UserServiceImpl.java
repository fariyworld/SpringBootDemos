package com.bonc.service.impl;

import com.bonc.domain.User;
import com.bonc.mapper_m.UserMapper;
import com.bonc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * <br />
 * Created by mace on 15:54 2018/4/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper_tk;

    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);

        return userMapper_tk.findByPage();
    }
}
