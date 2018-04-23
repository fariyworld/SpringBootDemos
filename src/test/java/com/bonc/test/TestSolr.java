package com.bonc.test;

import com.bonc.entity.Users;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: Created by mace on 10:33 2018/4/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolr {

    public static String url = "http://192.168.88.128:8983/solr/collection1";

    @Test
    public void search(){

        HttpSolrClient solrClient = new HttpSolrClient(url);

        SolrQuery query = new SolrQuery("name:尚");

        try{

            QueryResponse response = solrClient.query(query);

            SolrDocumentList documents = response.getResults();

            for (SolrDocument document : documents) {

                System.out.println(document.toString());
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }
}
