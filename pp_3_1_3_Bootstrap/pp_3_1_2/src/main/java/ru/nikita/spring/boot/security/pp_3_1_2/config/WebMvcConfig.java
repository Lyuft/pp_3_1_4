package ru.nikita.spring.boot.security.pp_3_1_2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/admin").setViewName("/admin/all-users");
        registry.addViewController("/user").setViewName("/user");
        registry.addViewController("/auth/login").setViewName("/auth/login");
    }
}
