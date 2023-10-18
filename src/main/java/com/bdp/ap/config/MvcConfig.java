package com.bdp.ap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bdp.ap.common.interceptor.MenuCheckInterceptor;

/**
 * MVC 셋팅
 * Async 서비스 enable
 */
@Configuration
@EnableAsync
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 서브메뉴 처리를 위한 인터셉터 추가
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(menuCheckInterceptor())
        .excludePathPatterns("/css/**", "/js/**", "/images/**", "/font/**", "/webjars/**","/favicon.ico","/","/login","/error/**","/test/**","/dispatch/**", "/pub/**");
    }

    @Bean
    public MenuCheckInterceptor menuCheckInterceptor() {
        return new MenuCheckInterceptor();
    }

}
