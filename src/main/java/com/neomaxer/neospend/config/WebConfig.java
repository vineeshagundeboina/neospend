package com.neomaxer.neospend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	PrivilegeInterceptor privilegeInterceptor;

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(privilegeInterceptor).excludePathPatterns("/v3/api-docs", "/v3/api-docs/*",
				"/swagger-ui.html", "/swagger-ui/**", "/static/**", "/api/auth/sendpdf", "/js/**", "/webjars/**");
	}
}
