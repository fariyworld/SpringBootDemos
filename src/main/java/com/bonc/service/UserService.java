package com.bonc.service;

import com.bonc.domain.User;
import com.github.pagehelper.PageInfo;

/**
 * description:
 * <br />
 * Created by mace on 15:53 2018/4/28.
 */
public interface UserService {

    PageInfo<User> findByPage(int pageNum, int pageSize);
}
