package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author starsea
 * @date 2021-01-22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/";
        registry.addResourceHandler("/avatar/**", "/petimg/**", "/defaultavatar/**", "/defaultpetimg/**")
                .addResourceLocations("file:" + basePath + "avatar/", "file:" + basePath + "petimg/", "file:" + basePath + "defaultavatar/", "file:" + basePath + "defaultpetimg/");
    }
}
