package com.bonc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 22:43 2018/5/12.
 */
@Component
@ConfigurationProperties(prefix = "MD5")
public class MD5Properties {

    public static String salt;

    public static String getSalt() {
        return salt;
    }

    public static void setSalt(String salt) {
        MD5Properties.salt = salt;
    }
}
