package com.bonc.common;

import com.github.pagehelper.PageInfo;

/**
 * description:
 * <br />
 * Created by mace on 17:02 2018/4/27.
 */
public interface BaseMapper<T>{

    PageInfo<T> findByPage();
}
