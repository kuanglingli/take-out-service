package com.itaem.crazy.shirodemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */
//        registry.addResourceHandler("/static/file/").addResourceLocations("file:///C:/soft/workspace/shiro-auth-master/src/main/resources/static/file/");
        //苹果电脑路径规则跟windows不一样，请自行修改
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+System.getProperty("user.dir")+"\\src\\main\\resources\\static\\file\\");
    }
}
