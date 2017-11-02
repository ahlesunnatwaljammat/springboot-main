package edu.learn.rest.config;

import edu.learn.rest.filter.CORSFilter;
import edu.learn.rest.filter.URebalAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean urebalAuthorizationFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("uRebal Authorization Filter");
        URebalAuthorizationFilter uRebalAuthorizationFilter = new URebalAuthorizationFilter();
        registrationBean.setFilter(uRebalAuthorizationFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean urebalCorsFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("uRebal Cors Filter");
        CORSFilter corsFilter = new CORSFilter();
        registrationBean.setFilter(corsFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
