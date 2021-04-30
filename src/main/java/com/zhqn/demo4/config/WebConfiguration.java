package com.zhqn.demo4.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileName: WebConfiguration
 * Date:     2021/4/29 18:40
 * Description:
 *
 * @author zhouquan3
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${allowOrigin:http://localhost:8080}")
    private String allowOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowOrigin)
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }
}
