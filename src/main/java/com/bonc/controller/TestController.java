package com.bonc.controller;

import com.bonc.dao.StudentDao;
import com.bonc.entity.Users;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    private SolrClient solrClient;

    @Autowired
    private SolrTemplate solrTemplate;

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

    @RequestMapping(value = "/findByName2Solr/{name}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Users> findByName2Solr(@PathVariable String name){

        SolrQuery query = new SolrQuery("name:"+name);

        try{

            QueryResponse response = solrClient.query("collection1",query);

            List<Users> usersList = response.getBeans(Users.class);

            return usersList;

            /*SolrDocumentList documents = response.getResults();

            for (SolrDocument document : documents) {

                return document.toString();
            }*/

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

        return null;
    }

    @RequestMapping(value = "/findById2Solr/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Users findById2Solr(@PathVariable String id){

        return solrTemplate.getById("collection1", id, Users.class);
    }

}
