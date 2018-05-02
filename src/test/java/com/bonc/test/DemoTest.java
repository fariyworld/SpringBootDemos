package com.bonc.test;

import com.bonc.util.MD5Util;
import org.junit.Test;

/**
 * description: 测试用例
 * <br />
 * Created by mace on 16:55 2018/5/2.
 */
public class DemoTest {

    @Test
    public void testMD5(){

        for (int i=0; i<5; i++){

            String s = MD5Util.MD5Encode("liuye0425");

            System.out.println(s);
        }
    }
}
