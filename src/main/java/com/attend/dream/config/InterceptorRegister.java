package com.attend.dream.config;

import com.attend.dream.component.LoginHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class InterceptorRegister implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration res = registry.addInterceptor(new LoginHandlerInterceptor());
        res.addPathPatterns("/**").excludePathPatterns("/login","/user/login","/");
    }
}
