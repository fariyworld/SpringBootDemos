package com.bonc.controller;

import com.bonc.common.ResponseMessage;
import com.bonc.domain.User;
import com.bonc.entity.Users;
import com.bonc.enums.ResponseCode;
import com.bonc.service.IUserService;
import com.bonc.service.UsersService;
import com.bonc.util.FTPUtil;
import com.bonc.util.MongoOpsUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @Description:
 *
 * Created by mace on 9:37 2018/4/9.
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private UsersService usersService;

    @Autowired
    private IUserService iUserService;


    /**
     *
     * 模板类freemark伪静态
     * @param map
     * @return String
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map){

        map.addAttribute("name", "hello freeMarker");

        map.addAttribute("sex", 1);

        return "home";
    }


    @RequestMapping(value = "/testThymeleaf", method = RequestMethod.GET)
    public String testThymeleaf(ModelMap map){

        map.addAttribute("hello", "hello springboot Thymeleaf");

        return "HelloThymeleaf";
    }

    @RequestMapping("/testLog")
    @ResponseBody
    public String testLog(){

        logger.info("测试 slf4j logback-spring.xml 记录日志");

        int i = 1/0;

        return "success";
    }

    @RequestMapping("/testEx")
    @ResponseBody
    public String testEx() throws Exception{

        int i = 1/0;

        return "success";
    }


    @RequestMapping(value = "/findById2Solr/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Users findById2Solr(@PathVariable String id){

        return solrTemplate.getById("collection1", id, Users.class);
    }


    @RequestMapping(value = "/getUsersById2Mysql/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<Users> getUsersById2Mysql(@PathVariable Integer id){

        return ResponseMessage.createBySuccess(usersService.getUsersById(id));
    }


    @RequestMapping(value = "/insertUsers2Mysql", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertUsers2Mysql(Users user){

        return usersService.insertUsers(user);
    }

    @RequestMapping(value = "/testMybatis_insert", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<Integer> testMybatis_insert(User user){

        return ResponseMessage.createBySuccess(iUserService.insert(user));
    }


    @RequestMapping(value = "/testMybatis_tk_pagehelper", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<PageInfo<User>> testMybatis_tk_pagehelper(){

        return ResponseMessage.createBySuccess(iUserService.findByPage(1,2));
    }


    @RequestMapping(value = "/testAjax.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage testAjax(String username, String password){

        if(StringUtils.isBlank(password) || StringUtils.isBlank(password)){
            return ResponseMessage.createByErrorResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
        }

        if("admin".equals(username) && "admin".equals(password)){
            return ResponseMessage.createBySuccessMessage("登录成功");
        }

        return ResponseMessage.createByErrorMessage("登录失败");
    }


    @RequestMapping(value = "/testUploadFile.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> testUploadFile(@RequestParam(value = "photo", required = false) MultipartFile file){

        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = MongoOpsUtil.ObjectId2Str() + fileExtensionName;

        logger.info("开始上传文件,上传文件的文件名:{},新文件名:{}",fileName,uploadFileName);

        try {
            boolean flag = FTPUtil.uploadImage(uploadFileName, file.getInputStream());
            if(!flag)
                return ResponseMessage.createByErrorMessage("文件上传FTP服务器失败");
        } catch (IOException e) {
            logger.error("文件上传失败");
            return ResponseMessage.createByErrorMessage("文件上传失败");
        }
        return ResponseMessage.createBySuccess("文件上传FTP服务器成功",uploadFileName);
    }

}
