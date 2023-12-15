package com.cdp.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		String[] allowedOrigins = { "http://localhost:3000", "https://localhost", "https://cdpdev.koreanair.com", "https://cdp.koreanair.com"};
		registry.addMapping("/**").allowedOriginPatterns(allowedOrigins).allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowCredentials(true);
	}

}
