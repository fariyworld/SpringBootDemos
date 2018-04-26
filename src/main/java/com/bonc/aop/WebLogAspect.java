package com.bonc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Before(value = "webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("=========================请求 start======================================");
//        logger.info("URL : "+ request.getRequestURL());
        logger.info("IP : " + getClientIp(request));
        logger.info("URL : "+ URLDecoder.decode(request.getRequestURL().toString(), "UTF-8"));
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName()
                                      + "." + joinPoint.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        if ( parameterMap.size() > 0 ){
            logger.info("request参数===========================================start");
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for(Map.Entry<String, String[]> entry:entrySet){
                String key = entry.getKey();
                logger.info("name: { "+key+" }, value: { "+request.getParameter(key)+" }");
            }
            logger.info("request参数===========================================end");
        }
        if(!Arrays.toString(joinPoint.getArgs()).equals("[{}]")){
            logger.info("请求参数为: "+ Arrays.toString(joinPoint.getArgs()));
        }

    }

    @AfterReturning(returning = "result",pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable{

        // 处理完请求，返回内容
        logger.info("RESPONSE_TYPE : " + result.getClass());
        logger.info("RESPONSE : " + result);
        logger.info("=========================请求 end======================================");
//      logger.info("\t");
        System.out.println("\t");
    }

    public  String getClientIp(HttpServletRequest request){

        String remoteAddr = "";

        if(request!=null){

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if(remoteAddr==null || "".equals(remoteAddr)){

                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
