package com.cdevs.queene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cdevs.queene.validations.AuthFilter;

@SpringBootApplication
public class QueeneApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueeneApplication.class, args);
	}
    
    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/api/v1/my-appointments");
        registrationBean.addUrlPatterns("/api/v1/book");
        registrationBean.addUrlPatterns("/api/v1/delete/*");
        registrationBean.addUrlPatterns("/api/v1/all-clients");
        registrationBean.addUrlPatterns("/api/v1/all-employees");
        registrationBean.addUrlPatterns("/api/v1/all");
        registrationBean.addUrlPatterns("/api/v1/find/*");

        return registrationBean;
    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/*").allowedOrigins("*");
			}
        };
    }
}