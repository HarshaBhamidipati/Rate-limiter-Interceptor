package com.harb.ratelimiter.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitServiceInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(RateLimitServiceInterceptor.class);
    @Value("${minimum.req.in.min}")
    int maxNumberOfReqinMin;
    Map<String,Integer> counterMap = new ConcurrentHashMap<>();
    boolean flag =true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Date d = new Date();
        String currentMin = String.valueOf(d.getMinutes());
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

    public void clearMap(){
        logger.info("Clearing Map after an hour");
        counterMap.clear();
    }

}
