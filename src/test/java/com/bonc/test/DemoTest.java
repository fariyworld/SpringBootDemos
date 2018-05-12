package com.bonc.test;

import com.alibaba.fastjson.JSON;
import com.bonc.domain.User;
import com.bonc.util.MD5Util;
import com.bonc.util.MongoOpsUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

/**
 * description: 测试用例
 * <br />
 * Created by mace on 16:55 2018/5/2.
 */
public class DemoTest {

    @Test
    public void testMD5(){

        String s = MD5Util.encode("liuye0425");

        System.out.println(s);
    }

    @Test
    public void test1(){

        List<String> userList = Lists.newArrayList();

        User user = new User();

        user.setUsername("fariy");
        user.setPassword("123456");
        user.setCreate_time(MongoOpsUtil.time2Id().getDate());

        String jsonString = JSON.toJSONString(user);

        System.out.println(jsonString);

        userList.add(jsonString);
    }

    @Test
    public void test2(){

//        System.out.println(MongoOpsUtil.id2time("5af6e967ea472332e0a52018"));
    }

}
