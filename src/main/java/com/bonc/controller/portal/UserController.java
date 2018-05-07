package com.bonc.controller.portal;

import com.bonc.common.Constant;
import com.bonc.common.ResponseMessage;
import com.bonc.domain.User;
import com.bonc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * description: mmall user
 * <br />
 * Created by mace on 11:21 2018/5/3.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /**
     * description: 门户登录 请求方法为post
     * <br /><br />
     * create by mace on 2018/5/3 12:21.
     * @param session
     * @param username
     * @param password
     * @return: com.bonc.common.ResponseMessage<com.bonc.domain.User>
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ResponseMessage<User> login(HttpSession session, String username, String password){

        ResponseMessage<User> response = iUserService.login(username, password);
        if( response.isSuccess() ){
            //登录成功 把当前用户放到session中
            session.setAttribute(Constant.CURRENT_USER, response.getData());
        }
        //无论成功与否返回给前端的都是 response
        return response;
    }


    /**
     * description: 获取当前登录用户信息
     * <br /><br />
     * create by mace on 2018/5/3 13:09.
     * @param session
     * @return: com.bonc.common.ResponseMessage<com.bonc.domain.User>
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    public ResponseMessage<User> getUserInfo(HttpSession session){

        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        if(user != null){
            return ResponseMessage.createBySuccess(user);
        }
        return ResponseMessage.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    /**
     * description: 注销
     * <br /><br />
     * create by mace on 2018/5/3 13:15.
     * @param session
     * @return: com.bonc.common.ResponseMessage<java.lang.String>
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ResponseMessage<String> logout(HttpSession session){

        if ( session.getAttribute(Constant.CURRENT_USER) == null ){
            //未登录
            return ResponseMessage.createByErrorMessage("用户未登录,无法注销");
        } else{
            //已登录
            session.removeAttribute(Constant.CURRENT_USER);
            return ResponseMessage.createBySuccess();
        }
    }

}
