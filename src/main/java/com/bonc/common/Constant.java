package com.bonc.common;

/**
 * description: 常量
 * <br />
 * Created by mace on 12:02 2018/5/3.
 */
public class Constant {

    //当前用户常量 key
    public static final String CURRENT_USER = "current_user";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //角色
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
