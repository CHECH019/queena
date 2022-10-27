package com.cdevs.queene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cdevs.queene.global.Constants;
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
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/my-appointments");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/book");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/delete/*");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/all-clients");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/all-employees");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/all");
        registrationBean.addUrlPatterns(Constants.BASE_URL+"/find/*");

        return registrationBean;
    }
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(Constants.BASE_URL+"/*").allowedOrigins("*");
                
                registry.addMapping(Constants.BASE_URL+"/service/*").allowedOrigins("*");
			}
        };
    }
}