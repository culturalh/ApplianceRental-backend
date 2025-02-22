package com.jxau.li.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 前端图片访问路径，静态资源
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将本地文件路径映射为 HTTP 访问路径
        registry.addResourceHandler("/file/**")               // 访问路径
                .addResourceLocations("file:" + uploadDir);   // 本地目录
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许前端域名访问（解决跨域问题）
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9000") // 前端域名
                .allowedMethods("*");
    }
}