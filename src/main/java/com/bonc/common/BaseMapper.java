package com.bonc.common;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 17:02 2018/4/27.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

    PageInfo<T> findByPage();
}
