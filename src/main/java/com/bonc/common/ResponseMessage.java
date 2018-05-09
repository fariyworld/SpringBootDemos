package com.bonc.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.bonc.enums.ResponseCode;

import java.io.Serializable;

/**
 * @Description: 统一处理返回前端的消息
 *
 * Created by mace on 17:49 2018/4/26.
 */
public class ResponseMessage<T> implements Serializable {

    private static final long serialVersionUID = -6383969738771150638L;

    //状态码
    private int status_code;
    //消息描述
    private String msg;
    //返回数据
    private T data;

    private ResponseMessage(int status_code){
        this.status_code = status_code;
    }
    private ResponseMessage(int status_code,T data){
        this.status_code = status_code;
        this.data = data;
    }

    private ResponseMessage(int status_code,String msg,T data){
        this.status_code = status_code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseMessage(int status_code,String msg){
        this.status_code = status_code;
        this.msg = msg;
    }

    //使之不在json序列化结果当中
    @JSONField(serialize = false)
    public boolean isSuccess(){
        return this.status_code == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status_code;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return msg;
    }

    /**
     * description: 请求成功,返回 ---> 0,success
     * <br /><br />       
     * create by mace on 2018/4/27 14:48.
     * @param   
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg()
        );
    }

    /**
     * description: 请求成功,返回 ---> 0,自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:48.
     * @param msg  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccessMessage(String msg){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                msg
        );
    }

    /**
     * description: 请求成功，返回0,success,data
     * <br /><br />       
     * create by mace on 2018/4/27 14:44.
     * @param data  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(T data){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                data
        );
    }

    /**
     * description: 请求成功，返回0,自定义message,data
     * <br /><br />
     * create by mace on 2018/4/27 14:50.
     * @param msg
     * @param data
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(String msg,T data){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                msg,
                data
        );
    }

    /**
     * description: 请求失败,返回-10，error
     * <br /><br />       
     * create by mace on 2018/4/27 14:52.
     * @param   
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByError(){
        return new ResponseMessage<T>(
                ResponseCode.ERROR.getCode(),
                ResponseCode.ERROR.getMsg()
        );
    }

    /**
     * description: 请求失败,返回-10，自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:52.
     * @param errorMessage  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorMessage(String errorMessage){
        return new ResponseMessage<T>(
                ResponseCode.ERROR.getCode(),
                errorMessage
        );
    }

    /**
     * description: 请求失败,返回自定义状态码，自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:53.
     * @param errorCode
     * @param errorMessage
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ResponseMessage<T>(
                errorCode,
                errorMessage
        );
    }

    /**
     * description: 请求失败,根据ResponseCode返回
     * <br /><br />
     * create by mace on 2018/5/7 11:10.
     * @param responseCode
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorResponseCode(ResponseCode responseCode){
        return new ResponseMessage<T>(
                responseCode.getCode(),
                responseCode.getMsg()
        );
    }

    /**
     * description: 请求失败,根据ResponseCode返回, data
     * <br /><br />
     * create by mace on 2018/5/9 9:46.
     * @param responseCode
     * @param data
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorResponseCode(ResponseCode responseCode, T data){
        return new ResponseMessage<T>(
                responseCode.getCode(),
                responseCode.getMsg(),
                data
        );
    }

}
