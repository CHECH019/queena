package com.cdevs.queena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cdevs.queena.validations.AuthFilter;

@SpringBootApplication
public class QueenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueenaApplication.class, args);
	}
    
    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/api/v1/client/my-appointments");
        registrationBean.addUrlPatterns("/api/v1/employee/my-appointments");
        registrationBean.addUrlPatterns("/api/v1/appointment/book");
        return registrationBean;
    }
}