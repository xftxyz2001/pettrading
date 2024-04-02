package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyConfig myConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = Paths.get(myConfig.getUploadDirectory()).toAbsolutePath().toString();
        List<String> paths = Arrays.asList("avatar", "petimg", "defaultavatar", "defaultpetimg");
        for (String path : paths) {
            registry.addResourceHandler("/" + path + "/**")
                    .addResourceLocations("file:" + Paths.get(basePath, path).toAbsolutePath() + "/");
        }
    }
}
