package com.bonc;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: SpringBoot启动Application
 *
 * Created by mace on 9:29 2018/4/9.
 */
@SpringBootApplication
//扫描mybatis--->mapper_solr
//@MapperScan(basePackages = {"com.bonc.mapper","com.bonc.mapper_solr"})
//开启事务管理
//@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    /**
     * @Description: SpringBoot实现FastJson解析json数据 统一设置时间戳转换
     * 格式化日期格式
     * @JSONField(format = "yyyy-MM-dd HH:mm:ss")
     * 不进行序列化
     * @JSONField(serialize = false)
     * SerializerFeature属性
     *
     * @return: org.springframework.boot.autoconfigure.web.HttpMessageConverters
     * Created by mace on 2018/4/27 11:48.
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        fastConverter.setFeatures(
                    SerializerFeature.PrettyFormat,
                    //统一转换时间戳: yyyy-MM-dd HH:mm:ss
                    SerializerFeature.WriteDateUseDateFormat,
                    //输出为null的
                    SerializerFeature.WriteMapNullValue
                );

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);
    }

}
