package com.bonc.controller;

import com.bonc.common.ResponseMessage;
import com.bonc.dao.StudentDao;
import com.bonc.entity.Users;
import com.bonc.gdao_common_mapper.UserMapper;
import com.bonc.gdomain_common_mapper.User;
import com.bonc.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description:
 *
 * Created by mace on 9:37 2018/4/9.
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @TODO: 模板类freemark 伪静态
     * @param map
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map){

        map.addAttribute("name", "hello freeMarker");

        map.addAttribute("sex", 1);

        return "home";
    }

    @RequestMapping("/testLog")
    @ResponseBody
    public String testLog(){

        logger.info("测试 slf4j logback-spring.xml 记录日志");

        return "success";
    }

    @RequestMapping(value = "/findById2Solr/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Users findById2Solr(@PathVariable String id){

        return solrTemplate.getById("collection1", id, Users.class);
    }


    @RequestMapping(value = "/getUsersById2Mysql/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<Users> getUsersById2Mysql(@PathVariable Integer id){

//        return usersService.getUsersById(id);

        return ResponseMessage.createBySuccess(usersService.getUsersById(id));
    }


    @RequestMapping(value = "/insertUsers2Mysql", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertUsers2Mysql(Users user){

        return usersService.insertUsers(user);
    }

    @RequestMapping(value = "/testMybatis", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<User> testMybatis(){

        return ResponseMessage.createBySuccess(userMapper.selectByPrimaryKey(1));
    }
}
