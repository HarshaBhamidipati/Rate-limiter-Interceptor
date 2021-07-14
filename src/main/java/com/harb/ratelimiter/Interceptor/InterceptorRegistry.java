package com.harb.ratelimiter.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorRegistry implements WebMvcConfigurer {

    @Autowired
    RateLimitServiceInterceptor rateLimitServiceInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitServiceInterceptor).addPathPatterns("/books");
    }
}
