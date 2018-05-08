package com.bonc.common;

import com.bonc.aop.WebLogAspect;
import com.bonc.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * description: 全局异常处理 返回json
 * <br />
 * Created by mace on 11:58 2018/5/8.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage<ExceptionInfo<Exception>> errorHandler(Exception ex, HttpServletRequest request, HandlerMethod handlerMethod){

        ExceptionInfo<Exception> exceptionInfo = new ExceptionInfo<Exception>();

        exceptionInfo.setException(ex);
        exceptionInfo.setIp(WebLogAspect.getClientIp(request));
        exceptionInfo.setUrl(WebLogAspect.getRequestUrl(request));
        exceptionInfo.setClass_method(handlerMethod.getMethod().toGenericString());
        exceptionInfo.setEx_message(ex.getMessage());

        return ResponseMessage.createByErrorResponseCode(ResponseCode.EXCEPTION,exceptionInfo);
    }
}
