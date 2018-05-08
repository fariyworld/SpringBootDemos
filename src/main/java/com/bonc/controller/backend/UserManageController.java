package com.bonc.controller.backend;

import com.bonc.common.Constant;
import com.bonc.common.ResponseMessage;
import com.bonc.domain.User;
import com.bonc.enums.ResponseCode;
import com.bonc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * description: 后台 user
 * <br />
 * Created by mace on 13:02 2018/5/7.
 */
@RestController
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ResponseMessage<User> login(HttpSession session, String username, String password){

        ResponseMessage<User> response = iUserService.login(username, password);
        //登录无论成功与否返回给前端的都是 response
        if(response.isSuccess()){
            //登录成功 判断是否为管理员角色
            if(response.getData().getRole().equals(Constant.Role.ROLE_ADMIN)){
                //是管理员  把当前用户放到session中
                session.setAttribute(Constant.CURRENT_USER, response.getData());
                return response;
            }else{
                //不是管理员
                return ResponseMessage.createByErrorResponseCode(ResponseCode.PERMISSION_DENIED);
            }
        }
        //登录失败
        return response;
    }
}
