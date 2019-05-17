package com.brzn.mtgboard.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

     @Bean
    public FilterRegistrationBean<CustomAuthFilter> loggingFilter() {
        FilterRegistrationBean<CustomAuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomAuthFilter());

        registrationBean.addUrlPatterns("/user/*");

        return registrationBean;

    }

}