package com.bonc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * description:
 * <br />
 * Created by mace on 15:37 2018/4/28.
 */
@Configuration
@ImportResource("classpath:spring-tr.xml")
public class TransactionManagerConfig {

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource){

        DataSourceTransactionManager txManager = new DataSourceTransactionManager();

        txManager.setDataSource(dataSource);

        return txManager;
    }
}
