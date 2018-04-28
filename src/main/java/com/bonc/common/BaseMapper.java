package com.bonc.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * description:
 * <br />
 * Created by mace on 17:02 2018/4/27.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

   /* int deleteByPrimaryKey(Integer id);

    int insert(T t);

    //不为空的放到sql
    int insertSelective(T t);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    //不为空的放到sql
    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);*/
}
