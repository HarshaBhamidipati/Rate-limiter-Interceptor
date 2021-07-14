package com.harb.ratelimiter.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RateLimitServiceInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RateLimitServiceInterceptor.class);
    int maxNumberOfReqinMin = 10;
    Map<String,Integer> counterMap = new HashMap<>();
    boolean flag =true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Date d = new Date();
        String currentMin = String.valueOf(d.getMinutes());
        if(currentMin.equals("0")&& flag){
            logger.info("HashMap size before clear is "+counterMap.size());
            counterMap.clear();
            logger.info("HashMap size after clear is "+counterMap.size());
            flag = false;
        }
        if(currentMin.equals("59") && !flag){
            logger.info("Flag is reverted!!");
            flag = true;
        }
        if(counterMap.containsKey(currentMin)){
            counterMap.put(currentMin,counterMap.get(currentMin)+1);
        }else{
            counterMap.put(currentMin,1);
        }
        logger.info("Counter value for minute "+ currentMin+" is "+counterMap.get(currentMin));
        if(counterMap.get(currentMin)>maxNumberOfReqinMin){
            response.setContentType("application/json");
            response.sendError(429);
            return false;
        }
        return true;
    }
}
