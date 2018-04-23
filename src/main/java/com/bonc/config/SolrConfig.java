package com.bonc.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Description: Created by mace on 18:14 2018/4/22.
 */
@Configuration
public class SolrConfig {

    @Bean
    @Scope(value = "singleton")
    public SolrTemplate solrTemplate(SolrClient solrClient){

        return new SolrTemplate(solrClient);
    }
}
