package com.cdp.portal.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cdp.portal.common.interceptor.AuthenticationInterceptor;

import lombok.RequiredArgsConstructor;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

	private final AuthenticationInterceptor authInterceptor;
//	private final LoggingInterceptor loggingInterceptor;
//	private final AccessApiInterceptor accessApiInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		List<String> urlPatterns = Arrays.asList("/**");

		List<String> common = Arrays.asList("/webjars/**", "/swagger-ui.html", "/api-docs", "/api-docs/**", "/swagger-ui/", "/swagger-ui/**",
				"/swagger-resources/**", "/health", "/error", "/bo/file/download/**", "/fo/file/download/**");

//		registry.addInterceptor(loggingInterceptor).addPathPatterns(urlPatterns).excludePathPatterns("/swagger-ui/**")
//				.excludePathPatterns("/v3/api-docs*").excludePathPatterns("/swagger-resources/**");

		registry.addInterceptor(authInterceptor).addPathPatterns(urlPatterns).excludePathPatterns(common);
	}
}