package com.bonc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 *
 * Created by mace on 9:29 2018/4/9.
 */
@SpringBootApplication
//扫描mybatis--->mapper
@MapperScan("com.bonc.mapper")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
