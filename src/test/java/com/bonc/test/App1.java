package com.bonc.test;

import com.bonc.util.MD5Util;
import com.bonc.util.MongoOpsUtil;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 测试
 *
 * Created by mace on 9:04 2018/4/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class App1 {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void test(){

        String objectId_hexStr = MongoOpsUtil.time2Id().toHexString();

        System.out.println("16进制的objectId: "+objectId_hexStr);

        System.out.println("时间戳: "+MongoOpsUtil.id2time(objectId_hexStr));
    }

    @Test
    public void test2(){

//        String encrypt = EncryptUtil.encode("liuye0425");

//        String encrypt = stringEncryptor.encrypt("liuye0425");

//        System.out.printf("加密后的密码: %s",encrypt);

//        System.out.println("解密后的密码: "+stringEncryptor.decrypt("etiOWmMphEuU1Ln92n3xqH033aUn26yT"));
        String encode = MD5Util.MD5Encode("liuye0425");

        System.out.println(encode);
    }
}
