package com.cdevs.queena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
                // TODO Auto-generated method stub
                registry.addMapping("/api/v1/*").allowedOrigins("*");
			}
        };
    }
    // @Bean
    // public FilterRegistrationBean<CorsFilter> corsFilter(){
    //     FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.addAllowedOrigin("*");
    //     config.addAllowedHeader("*");
    //     source.registerCorsConfiguration("/**", config);
    //     registrationBean.setFilter(new CorsFilter(source));
    //     registrationBean.setOrder(0);
    //     return registrationBean;   
    // }
}