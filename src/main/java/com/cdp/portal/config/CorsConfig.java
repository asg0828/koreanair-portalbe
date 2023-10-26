package com.cdp.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		String[] allowedOrigins = { "http://localhost:3000", "https://localhost", "https://newstpcdev.koreanair.com",
				"https://newstpcstg.koreanair.com", "https://newstpc.koreanair.com", "https://stpcdev.koreanair.com", "https://stpcstg.koreanair.com",
				"https://stpc.koreanair.com", "https://apidev.koreanair.com", "https://apistg.koreanair.com", "https://api.koreanair.com",
				"https://apim.koreanair.com", "https://apim.koreanair.com:3001" };
		registry.addMapping("/**").allowedOriginPatterns(allowedOrigins).allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowCredentials(true);
	}

}
