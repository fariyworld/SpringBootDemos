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

    public static final String ENCODING_CHARTSET = "UTF-8";

    public static final String FTP_OTHER_IMAGES_PATH = "images/other/";

    public interface Cookie{

        String OFF_LINE_SHOPPING_CART_ID = "off_line_shopping_cart_id";
        int HALF_HOUR = 30 * 60;
        int ONE_WEEK = 7 * 24 * 3600;
        int ONE_MONTH = 30 * 24 * 3600;
    }

    //角色
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
