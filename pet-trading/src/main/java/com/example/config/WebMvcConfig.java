package com.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final MyConfig myConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = Paths.get(myConfig.getUploadDirectory()).toAbsolutePath().toString();
        List<String> paths = Arrays.asList("avatar", "petimg", "defaultavatar", "defaultpetimg");
        for (String path : paths) {
            registry.addResourceHandler("/api/" + path + "/**")
                    .addResourceLocations("file:" + Paths.get(basePath, path).toAbsolutePath() + "/");
        }
    }
}
