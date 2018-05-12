package com.bonc.aop;

import com.bonc.common.Constant;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @Description: web请求 aop统一日志
 *
 * Created by mace on 17:13 2018/4/23.
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(* com.bonc.controller..*.*(..))")
    public void webLog() {
    }

    //前置通知
    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("=========================请求 start======================================");
//        logger.info("URL : "+ request.getRequestURL());
        logger.info("IP : " + getClientIp(request));
        logger.info("URL : "+ getRequestUrl(request));
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName()
                                      + "." + joinPoint.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        if ( parameterMap.size() > 0 ){
            logger.info("request参数===========================================start");
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for(Map.Entry<String, String[]> entry:entrySet){
                String key = entry.getKey();
                if(key.equals("passowrd")){
                    logger.info("name: { "+key+" }, value: {  }");
                }else{
                    logger.info("name: { "+key+" }, value: { "+request.getParameter(key)+" }");
                }
            }
            logger.info("request参数===========================================end");
        }else{
            if(!Arrays.toString(joinPoint.getArgs()).equals("[]")){
                logger.info("URL_PATH参数: "+ Arrays.toString(joinPoint.getArgs()));
            }
        }

    }

    //异常通知
    @AfterThrowing(throwing = "ex", pointcut = "webLog()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){

        logger.error("异常信息: {}",ex.getMessage());
        logger.error("异常堆栈: {}",ex.getStackTrace());
        logger.info("=========================请求 异常 end======================================");
    }

    //返回通知
    @AfterReturning(returning = "result",pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable{

        // 处理完请求，返回内容
        logger.info("RESPONSE_TYPE : " + result.getClass());
        logger.info("RESPONSE : " + result);
        logger.info("=========================请求 end======================================");
//      logger.info("\t");
        System.out.println("\t");
    }

    public static String getClientIp(HttpServletRequest request){

        String remoteAddr = StringUtils.EMPTY;;

        if(request!=null){

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if(remoteAddr==null || "".equals(remoteAddr)){

                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    public static String getRequestUrl(HttpServletRequest request){

        String url = StringUtils.EMPTY;

        try {
            url =  URLDecoder.decode(request.getRequestURL().toString(), Constant.ENCODING_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
