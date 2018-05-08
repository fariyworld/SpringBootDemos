package com.bonc.config;

import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Description: solr配置
 *
 * Created by mace on 18:14 2018/4/22.
 */
@Configuration
public class SolrConfig {

    private static final Logger logger = LoggerFactory.getLogger(SolrConfig.class);

    @Value("${spring.data.solr.host}")
    private String host;
    @Value("${spring.data.solr.username}")
    private String username;
    @Value("${spring.data.solr.password}")
    private String password;
    @Value("${spring.data.solr.maxConnections}")
    private int maxConnections;
    @Value("${spring.data.solr.maxConnectionsPerHost}")
    private int maxConnectionsPerHost;
    @Value("${spring.data.solr.followRedirects}")
    private boolean followRedirects;

    @Bean
    @Scope(value = "singleton")
    public SolrTemplate solrTemplate(){

        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, maxConnections);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, maxConnectionsPerHost);
        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, followRedirects);
//        solr安全认证
        params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, username);
        params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, password);

        HttpSolrClient solrClient = new HttpSolrClient(host, HttpClientUtil.createClient(params));

        logger.info("solr client success");

        return new SolrTemplate(solrClient);
    }
}
