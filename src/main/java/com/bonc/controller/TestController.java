package com.bonc.controller;

import com.bonc.aop.WebLogAspect;
import com.bonc.common.Constant;
import com.bonc.common.ResponseMessage;
import com.bonc.config.FTPProperties;
import com.bonc.domain.User;
import com.bonc.entity.Users;
import com.bonc.enums.ResponseCode;
import com.bonc.service.IUserService;
import com.bonc.service.UsersService;
import com.bonc.util.CookieUtil;
import com.bonc.util.FTPUtil;
import com.bonc.util.MongoOpsUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;


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
    public ResponseMessage<Map<String,String>> testUploadFile(@RequestParam(value = "photo", required = false) MultipartFile file){

        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = MongoOpsUtil.ObjectId2Str() + fileExtensionName;

        logger.info("开始上传文件,上传文件的文件名:{},新文件名:{}",fileName,uploadFileName);

        try {

            Map<String, MultipartFile> fileMap =  Maps.newHashMap();
            fileMap.put(uploadFileName, file);

            boolean flag = FTPUtil.uploadFile(Constant.FTP_OTHER_IMAGES_PATH, fileMap);

            if(flag){
                fileMap.clear();
                //上传成功
                Map<String,String> res = Maps.newHashMap();
                String url = FTPProperties.prefix + Constant.FTP_OTHER_IMAGES_PATH + uploadFileName;
                res.put("uri", uploadFileName);
                res.put("url", url);
                return ResponseMessage.createBySuccess("文件上传FTP服务器成功",res);
            }

        } catch (IOException e) {
            logger.error("文件上传失败");
            return ResponseMessage.createByErrorMessage("文件上传失败");
        }
        return ResponseMessage.createByErrorMessage("文件上传FTP服务器失败");
    }


    @RequestMapping(value = "/testSessionId.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> testSessionId(HttpSession session, HttpServletResponse response){

        String sessionId = session.getId();
        //D38353071D007BFB0FB6CA0282BAA2A3
        CookieUtil.set(response,
                      Constant.Cookie.OFF_LINE_SHOPPING_CART_ID,
                      MongoOpsUtil.ObjectId2Str()+"_离线购物车ID",
                       Constant.Cookie.ONE_MONTH);

        return ResponseMessage.createBySuccess();
    }

    @RequestMapping(value = "/getCookieByName.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> getCookieByName(HttpServletRequest request){

        String value = CookieUtil.getValueByName(request, Constant.Cookie.OFF_LINE_SHOPPING_CART_ID);

        logger.info(value);

        return ResponseMessage.createBySuccess(value);
    }

    @RequestMapping(value = "/setSessionId.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<String> setSessionId(HttpServletRequest request){

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        session.setAttribute("sessionId", sessionId);
        String requestUrl = WebLogAspect.getRequestUrl(request);
        session.setAttribute("message", "请求地址:"+requestUrl);

        return ResponseMessage.createBySuccessMessage("第一次访问"+requestUrl);
    }

    @RequestMapping(value = "/testRedisSession.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseMessage<Map<String,String>> getSessionId(HttpServletRequest request){

        Map<String,String> map = Maps.newHashMap();
        String sessionId = (String) request.getSession().getAttribute("sessionId");
        String message = (String) request.getSession().getAttribute("message");
        map.put("sessionId", sessionId);
        map.put("message", message);

        return ResponseMessage.createBySuccess(map);
    }
}
