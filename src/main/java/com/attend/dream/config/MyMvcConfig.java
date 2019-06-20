package com.attend.dream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        // super ?super.addViewControllers(registry);
//        registry.addViewController("/nihao").setViewName("login");
//        registry.addViewController("/zuce").setViewName("register");
        //访问localhost:8080默认访问页面
        //registry.addViewController("/").setViewName("index");
        //进入主页后，用于防止刷新页面表单重复提交，设置重定向
        //registry.addViewController("/index.html").setViewName("index");
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/nihao","/user/login","/user/register");
    }*/
}
