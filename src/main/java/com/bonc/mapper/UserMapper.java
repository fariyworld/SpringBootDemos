package com.bonc.mapper;

import com.bonc.common.BaseMapper;
import com.bonc.domain.User;
import com.github.pagehelper.PageInfo;

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
    PageInfo<User> findByPage();
}