package com.bonc.mapper;

import com.bonc.common.BaseMapper;
import com.bonc.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    @Override
    List<User> findByPage();

    /**
     * description: 检查用户名是否存在
     * <br /><br />       
     * create by mace on 2018/5/3 11:36.
     * @param username  
     * @return: int
     */
    int checkUserName(@Param("username") String username);

    User login(@Param("username") String username, @Param("password") String password);
}