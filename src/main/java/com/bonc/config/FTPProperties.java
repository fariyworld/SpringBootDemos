package com.bonc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 22:32 2018/5/12.
 */
@Component
@ConfigurationProperties(prefix = "ftp")
public class FTPProperties {

    public static String host;
    public static String username;
    public static String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
