package com.bonc.service;

import com.bonc.common.BaseService;
import com.bonc.common.ResponseMessage;
import com.bonc.domain.User;

/**
 * description:
 * <br />
 * Created by mace on 15:53 2018/4/28.
 */
public interface IUserService extends BaseService<User, Integer>  {

    ResponseMessage<User> login(String username, String password);
}
