package com.bonc.service.impl;

import com.bonc.domain.User;
import com.bonc.mapper.UserMapper;
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
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int insertSelective(User user) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return 0;
    }

    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        return userMapper.findByPage();
    }
}
