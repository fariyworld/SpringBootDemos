package com.bonc.service.impl;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bonc.common.ResponseMessage;
import com.bonc.domain.User;
import com.bonc.mapper.UserMapper;
import com.bonc.service.IUserService;
import com.bonc.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 15:54 2018/4/28.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

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
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
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

        List<User> userList = userMapper.findByPage();

        return new PageInfo<User>(userList);
    }

    /**
     * description: 门户登录
     * <br /><br />
     * create by mace on 2018/5/3 11:32.
     * @param username
     * @param password
     * @return: com.bonc.common.ResponseMessage<com.bonc.domain.User>
     */
    @Override
    public ResponseMessage<User> login(String username, String password){
        //1. 检查用户名是否存在
        if( userMapper.checkUserName(username) == 0 ){
            return ResponseMessage.createByErrorMessage("用户名不存在");
        }
        //2. MD5加密
        String md5Password = MD5Util.MD5Encode(password);
        //3. 登录
        User user = userMapper.login(username, md5Password);
        if( user == null ){
            return ResponseMessage.createByErrorMessage("密码错误");
        }
        //4.登录成功 --> 设置用户密码为""
        user.setPassword(StringUtils.EMPTY);
        return ResponseMessage.createBySuccess("登录成功", user);
    }
}
