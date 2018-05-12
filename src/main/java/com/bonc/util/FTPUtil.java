package com.bonc.util;

import com.bonc.config.FTPProperties;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: FTP服务器工具类
 * <br />
 * Created by mace on 14:24 2018/5/12.
 */
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private FTPClient ftpClient;

    private static String host;
    private static String username;
    private static String password;

    static {
        host = FTPProperties.host;
        username = FTPProperties.username;
        password = FTPProperties.password;
        logger.info("host:{}, username:{}, password:{}",host,username,"<>");
    }

    public static boolean uploadImage(String uploadFileName, InputStream in) throws IOException {

        return new FTPUtil().uploadFile("/data/ftpfile/images", uploadFileName, in);
    }

    private boolean uploadFile(String remotePath, String uploadFileName, InputStream in) throws IOException {

        boolean uploaded = true;
        if(connectServer(host, 21, username, password)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                ftpClient.storeFile(uploadFileName, in);
                logger.error("文件上传FTP服务器成功");
            } catch (IOException e) {
                logger.error("上传文件异常, {}",e.getMessage());
                uploaded = false;
            } finally {
                in.close();
                ftpClient.disconnect();
            }
        }else{
            uploaded = false;
            logger.error("文件上传FTP服务器失败");
        }
        return uploaded;
    }

    private boolean connectServer(String ip,int port,String user,String pwd){

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip,port);
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常, {}",e.getMessage());
            return isSuccess;
        }
        return isSuccess;
    }
}
