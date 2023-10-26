package com.cdp.portal.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info = @Info(title = "CDP API", description = "CDP API Spec", version = "v1")
)
@Configuration
public class SwaggerConfig {
    
    /**
     * bo 그룹
     * @return
     */
    @Bean
    public GroupedOpenApi boGroup() {
        final String[] path = {"/bo/**"};
        
        return GroupedOpenApi.builder()
                .group("bo")
                .pathsToMatch(path)
                .build();
    }
    
    /**
     * fo 그룹
     * @return
     */
    @Bean
    public GroupedOpenApi foGroup() {
        final String[] path = {"/fo/**"};
        
        return GroupedOpenApi.builder()
                .group("fo")
                .pathsToMatch(path)
                .build();
    }


}
